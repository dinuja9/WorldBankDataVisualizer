package GUIComponent;

import java.util.ArrayList;

import AnalysisComponent.AnalysisInterface;

/**
 * This class used for our observer design pattern where the list of viewers
 * gets updated when the user chooses to modify it. 
 *
 */
public class ViewObserver implements ObserverInterface {

	public ArrayList<AnalysisInterface> viewList = new ArrayList<AnalysisInterface>();

	/**
	 * Subscribe the analysis so it can be updated
	 * @param analysis
	 */
	public void subscribe(AnalysisInterface analysis) {
		viewList.add(analysis);
	}

	/**
	 * Use the specified parameters to notify the subscribers when a change has been made. 
	 * @param systemView
	 * @param chartType
	 * @param analysis
	 */
	public void notifySubs(boolean systemView, String chartType, String analysis) {
		for (int i = 0; i < viewList.size(); i++) {
			for (String chart : viewList.get(i).getChartSystemView().keySet()) {
				if (chart.equals(chartType)) {
					if (viewList.get(i).getName().equals(analysis)) {
						viewList.get(i).getChartSystemView().put(chart, systemView);
					}
				}
			}
		}
	}

	/**
	 * Get the list of viewers.
	 * @return the list of viewers.
	 */
	public ArrayList<AnalysisInterface> getViewList() {
		return this.viewList;
	}
}