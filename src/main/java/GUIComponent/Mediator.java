package GUIComponent;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class is the interface mediator that sets the login functions.
 */
public interface Mediator {
	public void login(JFrame LFrame);

	public void setLoginSection(LoginSection login);

	public void add(JTextField userName, JPasswordField password);
}
