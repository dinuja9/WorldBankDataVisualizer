package statsVisualiser.gui;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * The main functionality of this class is to set up the frame in order
 * to take the username and password of the user.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
@SuppressWarnings("serial")
public class LoginGUI extends JFrame implements ActionListener{
	private static JLabel ULabel;
	private static JTextField userName;
	private static JLabel PLabel;
	private static JPasswordField password;
	private static JButton submit;
	private static JFrame LFrame;
	
	/**
	 * Initializes the window for login.
	 */
	public LoginGUI() { 
		LFrame = new JFrame();
		JPanel LPanel = new JPanel(); 
		LFrame.setSize (350, 200);
		LFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LFrame.add(LPanel);
		LPanel.setLayout(null);
		
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
		
		submit = new JButton("Submit");
		submit.setBounds(130, 110, 90, 40);
		submit.addActionListener(this);
		LPanel.add(submit);
		LFrame.setVisible(true);
	}

	/**
	 * Action performed by the submit button. Makes a call
	 * to the login checker in order to validate the 
	 * credentials that were inputed.
	 */
	public void actionPerformed(ActionEvent e) {
		String uInput = userName.getText();
		String pInput = password.getText();
		try {
			LoginChecker checking = new LoginChecker(uInput, pInput);
			if(checking.checker()) {
				this.openMainApp();
			}
			else {	
				this.openError();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}

	/**
	 * Displays error message window if login credentials are invalid
	 */
	private void openError() {
		JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password!");
	// THESE 2 LINES WERE ADDED TO JUST RUN THE GUI NO MATTER WHAT THE USERNAME AND PASSWORD ENTERED WAS
		LFrame.setVisible(false);
		MainUI.Start(true);
	}

	/**
	 * Displays successful login message and takes user to main 
	 * application if login credentials are valid.
	 */
	private void openMainApp() {
		JOptionPane.showMessageDialog(null, "Correct Username and/or Password!");
		LFrame.setVisible(false);
		MainUI.Start(true);
	}
}