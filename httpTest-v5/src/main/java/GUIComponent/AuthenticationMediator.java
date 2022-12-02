package GUIComponent;
/**
 * This is the authentication class for mediator.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;	

public class AuthenticationMediator implements Mediator{
	private JTextField userName;
	private JPasswordField password;
	private LoginSection login;
	private JFrame LFrame;
	
	/**
	 * Sets the login value.
	 * @param login
	 */
	public AuthenticationMediator(LoginSection login) {
		this.login = login;
	}

	/**
	 * This method checks if the login is valid, and if it is then allow the user 
	 * access to the main UI. Otherwise it will throw an error.
	 */
	@Override
	public void login(JFrame LFrame) {
		this.LFrame = LFrame;
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
	}

	/**
	 * Displays successful login message and takes user to main 
	 * application if login credentials are valid.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	private void openMainApp() throws IOException, ParseException {
		JOptionPane.showMessageDialog(null, "Correct Username and/or Password!");
		LFrame.setVisible(false);
		MainUI.Start(true);
	}

	/**
	 * set the login
	 */
	@Override
	public void setLoginSection(LoginSection login) {
		this.login = login;
	}
	/**
	 * set the username and password.
	 */
	@Override
	public void add(JTextField userName, JPasswordField password) {
		this.userName = userName;
		this.password = password;
	}
}
