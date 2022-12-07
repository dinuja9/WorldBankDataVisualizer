package AnalysisComponent;

import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import RenderComponent.RenderFactory;
import RenderComponent.RenderInterface;

/**
 * This class makes sure the graph is available for the selected analysis, and then calls the RenderFactory class to get the graph. 
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */
public class AnalysisGraph {

	private AnalysisInterface analysis;
	private RenderInterface render;
	public String analysisType = "";

	/**
	 * Sets the graph with the type of analysis and graph.
	 * @param analysisType
	 * @param chartType
	 */
	public AnalysisGraph(String analysisType) {
		this.analysisType = analysisType;
	}

	/**
	 * Sets the type of analysis. 
	 * @param analysis
	 */
	public void setAnalysis(AnalysisInterface analysis) {
		this.analysis = analysis;
	}

	/**
	 * This method takes in the parameters that are given by the user and performs the analysis.
	 * It then checks to see if the graph is appropriate for the analysis, and if it is then it creates the graph.
	 * Otherwise, it throws an exception and shows an error message.
	 * 
	 * @param west
	 * @param country
	 * @param startDate
	 * @param endDate
	 * @param chartType
	 * @param systemView
	 * @throws exception if the graph is inappropriate for the type of analysis.
	 */
	public void executeStrategy(JPanel west, String country, int startDate, int endDate) {
		this.analysis.performAnalysis(west, country, startDate, endDate);
		HashMap<String, Boolean> chartSystemViewer = this.analysis.getChartSystemView();
		for (String keys : chartSystemViewer.keySet()) {
			if (chartSystemViewer.get(keys) == true) {
				try {
					RenderFactory factory = new RenderFactory();
					render = factory.createChart(keys, this.analysis.getDataSet(), west, this.analysisType,
							this.analysis.getReport());
					render.visualize();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Inappropriate view for this analysis!");
				}
			}
		}
	}
}
