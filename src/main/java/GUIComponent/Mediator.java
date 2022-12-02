package GUIComponent;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class is the interface mediator that sets the login functions.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */
public interface Mediator {
	public void login(JFrame LFrame);

	public void setLoginSection(LoginSection login);

	public void add(JTextField userName, JPasswordField password);
}
