package com.exia.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.exia.controller.FridgeController;

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
	private JLabel lbltatHumidity;
	private JLabel lbltatPourcent;
	private JLabel lbltatPointRosee;
	private JLabel lbltatPointRoseeDegree;
	private JLabel lblTempratureConsigne;
	private JLabel lblTempratureIntrieure;
	private JLabel lblTempratureIntrieureDegree;
	private JLabel lblTempratureDuModule;
	private JLabel lblTempratureDuModuleDegree;
	private JLabel lblTempratureExtrieure;
	private JLabel lblTempratureExtrieureDegree;
	private JLabel lblCondensation;
	private JLabel lblAugmentationAnormale;
	private JTextField condensationTextField;
	private JTextField anomalieTempTextField;
	private JTextField temperatureInterieurTextField;
	private JTextField temperatureModuleTextField;
	private TextField consigneTextField;
	private JTextField temperatureExterieureTextField;
	private JTextField humiditeTextField;
	private JTextField pointRoseeTextField;
	private Button button;
	private Button button_1;

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

				lbltatHumidity = new JLabel("Humidité : ");
				lbltatHumidity.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lbltatHumidity.setBounds(350, 50, 115, 16);
				panel_1.add(lbltatHumidity);
				
				lbltatPourcent = new JLabel("%");
				lbltatPourcent.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lbltatPourcent.setBounds(550, 50, 115, 16);
				panel_1.add(lbltatPourcent);
				
				lbltatPointRosee = new JLabel("Point de rosée : ");
				lbltatPointRosee.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lbltatPointRosee.setBounds(350, 80, 150, 16);
				panel_1.add(lbltatPointRosee);
				
				lbltatPointRoseeDegree = new JLabel("°C");
				lbltatPointRoseeDegree.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lbltatPointRoseeDegree.setBounds(600, 80, 150, 16);
				panel_1.add(lbltatPointRoseeDegree);

				lblTempratureConsigne = new JLabel("Température de consigne : ");
				lblTempratureConsigne.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureConsigne.setBounds(50, 53, 250, 24);
				panel_1.add(lblTempratureConsigne);
				
				lblTempratureIntrieure = new JLabel("Température intérieure : ");
				lblTempratureIntrieure.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureIntrieure.setBounds(50, 150, 250, 30);
				panel_1.add(lblTempratureIntrieure);
				
				lblTempratureIntrieureDegree = new JLabel("°C");
				lblTempratureIntrieureDegree.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureIntrieureDegree.setBounds(550, 150, 115, 16);
				panel_1.add(lblTempratureIntrieureDegree);

				lblTempratureDuModule = new JLabel("Température du module réfrigérant : ");
				lblTempratureDuModule.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureDuModule.setBounds(50, 185, 350, 30);
				panel_1.add(lblTempratureDuModule);
				
				lblTempratureDuModuleDegree = new JLabel("°C");
				lblTempratureDuModuleDegree.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureDuModuleDegree.setBounds(550, 185, 115, 16);
				panel_1.add(lblTempratureDuModuleDegree);

				lblTempratureExtrieure = new JLabel("Température extérieure : ");
				lblTempratureExtrieure.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureExtrieure.setBounds(50, 220, 250, 30);
				panel_1.add(lblTempratureExtrieure);
				
				lblTempratureExtrieureDegree = new JLabel("°C");
				lblTempratureExtrieureDegree.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblTempratureExtrieureDegree.setBounds(550, 220, 115, 16);
				panel_1.add(lblTempratureExtrieureDegree);

				lblCondensation = new JLabel("Condensation :");
				lblCondensation.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblCondensation.setBounds(50, 280, 250, 16);
				panel_1.add(lblCondensation);

				lblAugmentationAnormale = new JLabel("Augmentation anormale de température : ");
				lblAugmentationAnormale.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				lblAugmentationAnormale.setBounds(50, 320, 350, 30);
				panel_1.add(lblAugmentationAnormale);

				humiditeTextField = new JTextField("");
				humiditeTextField.setBounds(450, 50, 80, 22);
				humiditeTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				humiditeTextField.setEditable(false);
				panel_1.add(humiditeTextField);
				humiditeTextField.setColumns(10);
				
				pointRoseeTextField = new JTextField("");
				pointRoseeTextField.setBounds(500, 80, 80, 22);
				pointRoseeTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				pointRoseeTextField.setEditable(false);
				panel_1.add(pointRoseeTextField);
				pointRoseeTextField.setColumns(10);

				consigneTextField = new TextField("");
				consigneTextField.setEditable(false);
				consigneTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				consigneTextField.setBounds(120, 83, 65, 63);
				panel_1.add(consigneTextField);

				condensationTextField = new JTextField("");
				condensationTextField.setEditable(false);
				
				condensationTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				condensationTextField.setBounds(200, 280, 150, 22);
				panel_1.add(condensationTextField);
				condensationTextField.setColumns(10);

				anomalieTempTextField = new JTextField();
				anomalieTempTextField.setEditable(false);
				anomalieTempTextField.setColumns(10);
				anomalieTempTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
				anomalieTempTextField.setBounds(420, 320, 150, 22);
				panel_1.add(anomalieTempTextField);

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

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof HashMap) {
			HashMap<String, String> nouvelleValeur = (HashMap) arg1;
			for (Entry<String, String> valeur : nouvelleValeur.entrySet()) {
				if(valeur.getKey().equals("humidity")) {
					humiditeTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("tempint")) {
					temperatureInterieurTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("tempext")) {
					temperatureExterieureTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("temppeltier")) {
					temperatureModuleTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("pointrosee")) {
					pointRoseeTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("consigne")) {
					consigneTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("condensation")) {
					condensationTextField.setText(valeur.getValue());
				}
				if(valeur.getKey().equals("anomalie")) {
					anomalieTempTextField.setText(valeur.getValue());
				}
			}
		}
	}

}