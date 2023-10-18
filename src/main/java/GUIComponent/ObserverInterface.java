package GUIComponent;

import java.util.ArrayList;

import AnalysisComponent.AnalysisInterface;

/**
 * This class is the Observer Interface that has the functions to notify
 * subscribers and to subscribe other objects.
 */

public interface ObserverInterface {
	public void subscribe(AnalysisInterface analysis);

	public void notifySubs(boolean systemView, String chartType, String analysis);

	public ArrayList<AnalysisInterface> getViewList();
}
