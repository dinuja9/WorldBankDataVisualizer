package GUIComponent;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * The main functionality of this class is to set up the gui in order
 * to display the application.
 */

public class LoginGUI implements ActionListener {
	private JFrame LFrame;
	private Mediator mediator;
	
	/**
	 * This method creates the gui with the login component.
	 */
	public void createGUI() {
		JFrame LFrame = new JFrame();
		Container content = LFrame.getContentPane();
		LoginSection login = new LoginSection(mediator);
		content.add(login);
		mediator.setLoginSection(login);
	}
	/**
	 * this method sets the mediator
	 * @param mediator
	 */
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	/**
	 * Action performed by the submit button. Makes a call to the login checker in
	 * order to validate the credentials that were inputed.
	 */
	public void actionPerformed(ActionEvent e) {
		this.mediator.login(this.LFrame);
	}
}
