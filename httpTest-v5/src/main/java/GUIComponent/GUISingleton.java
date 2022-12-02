package GUIComponent;
/**
 * This is our singleton class that provides a single instance of the program.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */

public class GUISingleton {
	private static GUISingleton GUIObj;
	
	private GUISingleton() {
	}
	
	/**
	 * Creates a new instance of a singleton object
	 * @return a new instance of a singleton object
	 */
	public static GUISingleton getInstance() {
		if(GUIObj == null) {
			GUIObj = new GUISingleton();
		}
		return GUIObj;
	}
	
	/**
	 * This method starts the main application and initiates the login checker as well.
	 */
	public void startApplication() {
		LoginSection button = new LoginSection();
		Mediator mediator = new AuthenticationMediator(button);
		LoginGUI login = new LoginGUI();
		login.setMediator(mediator);
		login.createGUI();
	}
}
