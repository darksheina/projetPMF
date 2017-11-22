package com.exia.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ui.JPinboard;

import com.exia.controller.FridgeController;

import jssc.SerialNativeInterface;
import jssc.SerialPortList;

/**
 * Example of usage {@link JPinboard}.
 * 
 * @author Oleg Kurbatov &lt;o.v.kurbatov@gmail.com&gt;
 */
public class View implements Observer {

	private static Logger logger = Logger.getLogger(View.class);

	private static final JFrame INITIALIZATION_FRAME = new JFrame();

	private FridgeController controller;

	public View(FridgeController controller) {
		super();
		this.controller = controller;
	}
	private JFrame mainFrame;
	private JPanel panel;
	private JPanel panel_1;
	private Label label;
	private JLabel lbltatLed;
	private JLabel lblTempratureConsigne;
	private JLabel lblTempratureIntrieure;
	private JLabel lblTempratureDuModule;
	private JLabel lblTempratureExtrieure;
	private JLabel lblCondensation;
	private JLabel lblAugmentationAnormale;
	private JTextField anomalieTextField;
	private JTextField augmentTempTextField;
	private JTextField temperatureInterieurTextField;
	private JTextField temperatureModuleTextField;
	private TextField consigneTextField;
	private JTextField temperatureExterieureTextField;
	private JTextField textFieldEtatLED;
	private Button button;
	private Button button_1;
	private JButton btnAllumerteindreLed;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void run() throws IOException, InterruptedException {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainFrame = new JFrame("Projet PMF");
				mainFrame.setBounds(100, 100, 750, 500);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));

				panel = new JPanel();
				mainFrame.getContentPane().add(panel, BorderLayout.NORTH);
				panel.setLayout(new BorderLayout(0, 0));

				label = new Label("Frigo");
				label.setFont(new Font("Courier New", Font.PLAIN, 30));
				label.setAlignment(Label.CENTER);
				panel.add(label, BorderLayout.CENTER);

				panel_1 = new JPanel();
				mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);

				button = new Button("-");
				button.setBounds(10, 94, 79, 24);
				button.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				panel_1.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.diminuerConsigne();
					}
				});

				button_1 = new Button("+");
				button_1.setBounds(221, 94, 79, 24);
				button_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				panel_1.add(button_1);
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.augmenterConsigne();
					}
				});

				btnAllumerteindreLed = new JButton("Allumer/Éteindre LED");
				btnAllumerteindreLed.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				btnAllumerteindreLed.setBounds(500, 100, 180, 25);
				btnAllumerteindreLed.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							textFieldEtatLED.setText("" + controller.Allumer_eteindre());
						} catch (IllegalStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				panel_1.add(btnAllumerteindreLed);

				lbltatLed = new JLabel("État LED : ");
				lbltatLed.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lbltatLed.setBounds(489, 27, 115, 16);
				panel_1.add(lbltatLed);

				lblTempratureConsigne = new JLabel("Température de consigne : ");
				lblTempratureConsigne.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureConsigne.setBounds(50, 53, 250, 24);
				panel_1.add(lblTempratureConsigne);

				lblTempratureIntrieure = new JLabel("Température intérieure : ");
				lblTempratureIntrieure.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureIntrieure.setBounds(50, 150, 250, 30);
				panel_1.add(lblTempratureIntrieure);

				lblTempratureDuModule = new JLabel("Température du module réfrigérant : ");
				lblTempratureDuModule.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureDuModule.setBounds(50, 185, 350, 30);
				panel_1.add(lblTempratureDuModule);

				lblTempratureExtrieure = new JLabel("Température extérieure : ");
				lblTempratureExtrieure.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureExtrieure.setBounds(50, 220, 250, 30);
				panel_1.add(lblTempratureExtrieure);

				lblCondensation = new JLabel("Condensation :");
				lblCondensation.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblCondensation.setBounds(50, 280, 250, 16);
				panel_1.add(lblCondensation);

				lblAugmentationAnormale = new JLabel("Augmentation anormale de température : ");
				lblAugmentationAnormale.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblAugmentationAnormale.setBounds(50, 320, 350, 30);
				panel_1.add(lblAugmentationAnormale);

				textFieldEtatLED = new JTextField("");
				textFieldEtatLED.setBounds(589, 24, 116, 22);
				textFieldEtatLED.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				textFieldEtatLED.setEditable(false);
				panel_1.add(textFieldEtatLED);
				textFieldEtatLED.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// if led allumé : allumé
						// else éteinte
					}
				});
				textFieldEtatLED.setColumns(10);

				consigneTextField = new TextField("" + controller.getConsigne());
				consigneTextField.setEditable(false);
				consigneTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				consigneTextField.setBounds(120, 83, 65, 63);
				panel_1.add(consigneTextField);

				anomalieTextField = new JTextField();
				anomalieTextField.setEditable(false);
				anomalieTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				anomalieTextField.setBounds(420, 280, 250, 22);
				panel_1.add(anomalieTextField);
				anomalieTextField.setColumns(10);

				augmentTempTextField = new JTextField();
				augmentTempTextField.setEditable(false);
				augmentTempTextField.setColumns(10);
				augmentTempTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				augmentTempTextField.setBounds(420, 320, 116, 22);
				panel_1.add(augmentTempTextField);

				temperatureInterieurTextField = new JTextField();
				temperatureInterieurTextField.setEditable(false);
				temperatureInterieurTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				temperatureInterieurTextField.setBounds(420, 150, 116, 22);
				panel_1.add(temperatureInterieurTextField);
				temperatureInterieurTextField.setColumns(10);

				temperatureModuleTextField = new JTextField();
				temperatureModuleTextField.setEditable(false);
				temperatureModuleTextField.setColumns(10);
				temperatureModuleTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				temperatureModuleTextField.setBounds(420, 185, 116, 22);
				panel_1.add(temperatureModuleTextField);

				temperatureExterieureTextField = new JTextField();
				temperatureExterieureTextField.setEditable(false);
				temperatureExterieureTextField.setColumns(10);
				temperatureExterieureTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				temperatureExterieureTextField.setBounds(420, 220, 116, 22);
				panel_1.add(temperatureExterieureTextField);

				mainFrame.setVisible(true);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static String requestPort() {
		JComboBox<String> portNameSelector = new JComboBox<>();
		portNameSelector.setModel(new DefaultComboBoxModel<String>());
		String[] portNames;
		if (SerialNativeInterface.getOsType() == SerialNativeInterface.OS_MAC_OS_X) {
			// for MAC OS default pattern of jssc library is too restrictive
			portNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty\\..*"));
		} else {
			portNames = SerialPortList.getPortNames();
		}
		for (String portName : portNames) {
			portNameSelector.addItem(portName);
		}
		if (portNameSelector.getItemCount() == 0) {
			JOptionPane.showMessageDialog(null, "Cannot find any serial port", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(new JLabel("Port "));
		panel.add(portNameSelector);
		if (JOptionPane.showConfirmDialog(null, panel, "Select the port",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			return portNameSelector.getSelectedItem().toString();
		} else {
			System.exit(0);
		}
		return "";
	}

	private static void showInitializationMessage() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					JFrame frame = INITIALIZATION_FRAME;
					frame.setUndecorated(true);
					JLabel label = new JLabel("Connecting to device");
					label.setHorizontalAlignment(JLabel.CENTER);
					frame.getContentPane().add(label);
					frame.pack();
					frame.setSize(frame.getWidth() + 40, frame.getHeight() + 40);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
					frame.setLocation(x, y);
					frame.setVisible(true);
				}
			});
		} catch (InterruptedException | InvocationTargetException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static void hideInitializationWindow() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					INITIALIZATION_FRAME.setVisible(false);
					INITIALIZATION_FRAME.dispose();
				}
			});
		} catch (InterruptedException | InvocationTargetException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void addDevice(FirmataDevice device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		logger.debug("la view récupère le message arg1 : " + arg1.toString());
		if (arg1 instanceof String) {
			if (((String) arg1).startsWith("consigne=")) {
				logger.debug("la view met à jour le consigneTextField");
				consigneTextField.setText(((String) arg1).split("=")[1]);
			}
			if (((String) arg1).startsWith("tempInt=")) {
				logger.debug("la view met à jour le temperatureInterieurTextField");
				temperatureInterieurTextField.setText(((String) arg1).split("=")[1]);
			}
			if (((String) arg1).startsWith("tempExt=")) {
				logger.debug("la view met à jour le temperatureExterieureTextField");
				temperatureExterieureTextField.setText(((String) arg1).split("=")[1]);
			}
		}
	}

}