package GUIComponent;

import java.util.ArrayList;

import AnalysisComponent.AnalysisInterface;
/**
 * This class is the Observer Interface that has the functions to notify subscribers and to subscribe other objects.
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */

public interface ObserverInterface {
	public void subscribe(AnalysisInterface analysis);
	public void notifySubs(boolean systemView, String chartType, String analysis);
	public ArrayList<AnalysisInterface> getViewList();
}
