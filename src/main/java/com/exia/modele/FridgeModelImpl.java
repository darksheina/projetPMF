package com.exia.modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

public class FridgeModelImpl extends Observable implements FridgeModel {

	private static Logger logger = Logger.getLogger(FridgeModelImpl.class);
	
	private static final int PIN_NUMBER_FOR_SENSOR_TEMP = 5;

	private static final int PIN_NUMBER_FOR_LED = 13;
	
	IODevice device;

	private Pin led;

	private Pin sensorTemp;
	
	private int consigne = 15;

	private long temperatureInt = 0;
	
	private long temperatureExt = 3;

	public FridgeModelImpl() {

		device = new FirmataDevice("COM8");
		try {
			device.start();
			device.ensureInitializationIsDone();

			led = device.getPin(PIN_NUMBER_FOR_LED);
			led.setMode(Pin.Mode.OUTPUT);
			led.setValue(0);
			
			device.start();
			try {
				device.ensureInitializationIsDone();
			} catch (InterruptedException e) {
				System.exit(1);
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public long getValueForLed() {
		return led.getValue();
	}

	@Override
	public void setValueForLed(long value) {
		try {
			led.setValue(value);
			notifyObserver("led=" + value);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getConsigne() {
		return this.consigne;
	}

	long test = 0;
	
	@Override
	public long getValueForSensorTempInt() {
		// TODO : chercher la vrai valeur
		// return sensorTemp.getValue();
		return test++;
	}
	
	@Override
	public long getValueForSensorTempExt() {
		// TODO : chercher la vrai valeur
		// return sensorTemp.getValue();
		return test++;
	}
	
	@Override
	public void setConsigne(int consigne) {
		logger.debug("le modele met la valeur " + consigne + " en mémoire");
		this.consigne = consigne;
		logger.debug("le modele notifyObserver avec le message \"consigne=\" "+ consigne);
		notifyObserver("consigne=" + consigne);
	}
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(String message) {
		logger.info("notifyObserver " + message);
		for (Observer obs : listObserver)
			obs.update(this, message);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	@Override
	public void setTemperatureInt(long temperatureInt) {
		this.temperatureInt = temperatureInt;
		logger.debug("le modele notifyObserver avec le message \"consigne=\" "+ consigne);
		notifyObserver("tempInt=" + temperatureInt);
	}
	
	@Override
	public void setTemperatureExt(long temperatureExt) {
		this.temperatureExt = temperatureExt;
		logger.debug("le modele notifyObserver avec le message \"consigne=\" "+ consigne);
		notifyObserver("tempExt=" + temperatureExt);
	}
}