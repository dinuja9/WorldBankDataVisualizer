package GUIComponent;

/**
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */
public class ClientMain {
	/**
	 * This is the main class to initialize the entire application. To login, the
	 * username is "user" and the password is "12345" Refer to login.JSON for valid
	 * username/password combinations
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// SINGLETON DESIGN PATTERN USED HERE
		GUISingleton GUIObj;
		GUIObj = GUISingleton.getInstance();
		GUIObj.startApplication();
	}
}
