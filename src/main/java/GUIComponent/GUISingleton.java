package GUIComponent;

/**
 * This is our singleton class that provides a single instance of the program.
*/

public class GUISingleton {
	private static GUISingleton GUIObj;

	private GUISingleton() {
	}

	/**
	 * Creates a new instance of a singleton object
	 * 
	 * @return a new instance of a singleton object
	 */
	public static GUISingleton getInstance() {
		if (GUIObj == null) {
			GUIObj = new GUISingleton();
		}
		return GUIObj;
	}

	/**
	 * This method starts login component which translates to starting the main
	 * application.
	 */
	public void startApplication() {
		LoginSection button = new LoginSection();
		Mediator mediator = new AuthenticationMediator(button);
		LoginGUI login = new LoginGUI();
		login.setMediator(mediator);
		login.createGUI();
	}
}
