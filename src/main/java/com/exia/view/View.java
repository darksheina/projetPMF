package com.exia.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
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
	
	private TextField consigneTextField;
	private JTextField textField_6;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void run() throws IOException, InterruptedException {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			private JTextField textFieldEtatLED;
			private JFrame mainFrame;
			private JPanel panel;
			private JPanel panel_1;
			private Label label;
			private Button button;
			private Button button_1;
			private JLabel lbltatLed;
			private JLabel lblTempratureConsigne;
			private JLabel lblTempratureIntrieure;
			private JLabel lblTempratureDuModule;
			private JLabel lblTempratureExtrieure;
			private JLabel lblCondensation;
			private JTextField textField_2;
			private JLabel lblAugmentationAnormale;
			private JTextField textField_3;
			private JTextField textField_4;
			private JTextField textField_5;
			private JButton btnAllumerteindreLed;
			private TextArea textArea;
			
			@Override
			public void run() {

				mainFrame = new JFrame("Projet PMF");
				mainFrame.setBounds(100, 100, 784, 573);
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

				consigneTextField = new TextField("" + controller.getConsigne());
				consigneTextField.setEditable(false);
				consigneTextField.setBounds(120, 83, 65, 63);
				panel_1.add(consigneTextField);

				button = new Button("-");
				button.setBounds(10, 94, 79, 24);
				panel_1.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.diminuerConsigne();
					}
				});

				button_1 = new Button("+");
				button_1.setBounds(221, 94, 79, 24);
				panel_1.add(button_1);
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.augmenterConsigne();
					}
				});

				textFieldEtatLED = new JTextField("");
				textFieldEtatLED.setBounds(589, 24, 116, 22);
				textFieldEtatLED.setEditable(false);
				panel_1.add(textFieldEtatLED);
				textFieldEtatLED.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// if led allumé : allumé
						// else éteinte
					}
				});
				textFieldEtatLED.setColumns(10);

				lbltatLed = new JLabel("État LED : ");
				lbltatLed.setBounds(489, 27, 88, 16);
				panel_1.add(lbltatLed);

				lblTempratureConsigne = new JLabel("Température de consigne : ");
				lblTempratureConsigne.setBounds(50, 53, 182, 24);
				panel_1.add(lblTempratureConsigne);

				lblTempratureIntrieure = new JLabel("Température intérieure : ");
				lblTempratureIntrieure.setBounds(50, 151, 182, 30);
				panel_1.add(lblTempratureIntrieure);

				lblTempratureDuModule = new JLabel("Température du module réfrigérant : ");
				lblTempratureDuModule.setBounds(50, 188, 225, 30);
				panel_1.add(lblTempratureDuModule);
				
				lblTempratureExtrieure = new JLabel("Température extérieure : ");
				lblTempratureExtrieure.setBounds(50, 231, 182, 30);
				panel_1.add(lblTempratureExtrieure);
				
				lblCondensation = new JLabel("Condensation :");
				lblCondensation.setBounds(516, 191, 97, 16);
				panel_1.add(lblCondensation);
				
				textField_2 = new JTextField();
				textField_2.setBounds(625, 188, 116, 22);
				panel_1.add(textField_2);
				textField_2.setColumns(10);

				lblAugmentationAnormale = new JLabel("Augmentation anormale de température : ");
				lblAugmentationAnormale.setBounds(335, 223, 266, 16);
				panel_1.add(lblAugmentationAnormale);

				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(625, 220, 116, 22);
				panel_1.add(textField_3);

				textField_4 = new JTextField();
				textField_4.setEditable(false);
				textField_4.setBounds(203, 155, 116, 22);
				panel_1.add(textField_4);
				textField_4.setColumns(10);

				textField_5 = new JTextField();
				textField_5.setEditable(false);
				textField_5.setColumns(10);
				textField_5.setBounds(264, 192, 116, 22);
				panel_1.add(textField_5);

				textField_6 = new JTextField();
				textField_6.setEditable(false);
				textField_6.setColumns(10);
				textField_6.setBounds(203, 235, 116, 22);
				panel_1.add(textField_6);
				
				btnAllumerteindreLed = new JButton("Allumer/Éteindre LED");
				btnAllumerteindreLed.setBounds(504, 101, 182, 25);
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

				textArea = new TextArea();
				mainFrame.getContentPane().add(textArea, BorderLayout.SOUTH);
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
		logger.info("la view récupère le message arg1 : " + arg1.toString());
		if(arg1 instanceof String) {
			if(((String) arg1).startsWith("consigne=")) {
				logger.info("la view met à jour le consigneTextField");
				consigneTextField.setText(((String) arg1).split("=")[1]);
			}
			if(((String) arg1).startsWith("tempInt=")) {
				logger.info("la view met à jour le textField_6");
				textField_6.setText(((String) arg1).split("=")[1]);
			}
		}
	}
	
}