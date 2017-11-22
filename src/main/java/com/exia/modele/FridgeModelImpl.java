package com.exia.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class FridgeModelImpl extends Observable implements FridgeModel, SerialPortEventListener {

	private static Logger logger = Logger.getLogger(FridgeModelImpl.class);

	private int consigne = -1;

	SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { "COM10" };
	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
		// the next line is for Raspberry Pi and
		// gets us into the while loop and was suggested here was suggested
		// http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
		// System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent port
	 * locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				logger.info(inputLine);
				if (inputLine.startsWith("{") && inputLine.endsWith("}")) {
					Map<String, String> listValeur = new HashMap<>();
					String message = inputLine.replaceAll("\\{", "");
					message = message.replaceAll("\\}", "");
					String[] variables = message.split(",");
					for (String string : variables) {
						String[] valeur = string.split(":");
						listValeur.put(valeur[0], valeur[1]);
						if (valeur[0].equals("consigne")) {
							this.consigne = Integer.valueOf(valeur[1]);
						}
					}
					this.notifyObserver(listValeur);
				} else {
					logger.debug("message incomplet");
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public FridgeModelImpl() {

		this.initialize();
		Thread t = new Thread() {
			public void run() {
				// the following line will keep this app alive for 1000 seconds,
				// waiting for events to occur and responding to them (printing incoming
				// messages to console).
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
					logger.error(ie);
				}
			}
		};
		t.start();
		System.out.println("Started");

	}

	@Override
	public int getConsigne() {
		return this.consigne;
	}

	@Override
	public void setConsigne(int consigne) {
		if (this.consigne != -1) {
			OutputStream serialOut;
			try {
				serialOut = serialPort.getOutputStream();
				String s;
				if (consigne < 10) {
					s = "0" + consigne + "";
				} else {
					s = consigne + "";
				}
				serialOut.write(s.getBytes());
				serialOut.flush();
			} catch (IOException e) {
				logger.error(e);
			}
			logger.debug("le modele met la valeur " + consigne + " en mémoire");
			this.consigne = consigne;
			Map<String, String> nouvelleConsigne = new HashMap<>();
			nouvelleConsigne.put("consigne", consigne + "");
			notifyObserver(nouvelleConsigne);
		}
	}

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(Map<String, String> nouvelleValeur) {
		for (Observer obs : listObserver)
			obs.update(this, nouvelleValeur);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

}