package GUIComponent;

import java.awt.event.*;
import javax.swing.*;

/**
 * The main functionality of this class is to set up the frame in order to take
 * the username and password of the user.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
@SuppressWarnings("serial")
public class LoginSection extends JPanel implements ActionListener, Component {
	private static JFrame LFrame;
	private Mediator mediator;
	/**
	 * Initializes the window for login.
	 */
	public LoginSection() {
		
	}
	/**
	 * sets the medioator object and creates the login UI
	 * @param newMediator
	 */
	public LoginSection(Mediator newMediator) {
		setMediator(newMediator); 
		createGUI();
	}

	/**
	 * creates the login UI
	 */
	public void createGUI() {
		JLabel ULabel = new JLabel();
		JLabel PLabel = new JLabel();
		JTextField userName = new JTextField();
		JPasswordField password = new JPasswordField();
		JPanel LPanel = new JPanel();
		JButton submit = new JButton();

		ULabel = new JLabel("Username:");
		ULabel.setBounds(20, 20, 80, 25);
		LPanel.add(ULabel);

		userName = new JTextField();
		userName.setBounds(95, 20, 230, 25);
		LPanel.add(userName);

		PLabel = new JLabel("Password:");
		PLabel.setBounds(20, 70, 80, 25);
		LPanel.add(PLabel);

		password = new JPasswordField();
		password.setBounds(95, 70, 230, 25);
		LPanel.add(password);
		
		mediator.add(userName, password);
		
		LFrame = new JFrame();
		LFrame.setSize(350, 200);
		LFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LFrame.add(LPanel);
		LPanel.setLayout(null);
		submit = new JButton("Submit");
		submit.setBounds(130, 110, 90, 40);
		submit.addActionListener(this);
		LPanel.add(submit);
		LFrame.setVisible(true);
	}

	/**
	 * Action performed by the submit button. Makes a call to the login checker in
	 * order to validate the credentials that were inputed.
	 */
	public void actionPerformed(ActionEvent e) {
		this.mediator.login(LFrame);
	}
	/**
	 * sets the mediator
	 */
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

}