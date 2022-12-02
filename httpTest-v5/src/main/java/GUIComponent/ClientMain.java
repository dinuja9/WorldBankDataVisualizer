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
	 * Main method to initialize the entire app.
	 * @param args
	 */
	public static void main(String[] args) {
		//SINGLETON DESIGN PATTERN USED HERE
		GUISingleton GUIObj;
		GUIObj = GUISingleton.getInstance();
		GUIObj.startApplication();
	}
}
