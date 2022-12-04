package AnalysisComponent;

import java.util.ArrayList;

import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;

import APIComponent.GetData;
/**
 * 
 * This class is responsible for the 4th type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */

public class AnalysisFour implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public DefaultCategoryDataset dataset;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Average Forest area (as % of land area)";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisFour() {
		chartSystemView.put("Line Chart", false);
		chartSystemView.put("Scatter Chart", false);
		chartSystemView.put("Report", false);
		chartSystemView.put("Bar Chart", false);
		chartSystemView.put("Pie Chart", false);
	}

	/**
	 * This method is responsible for performing the type of analysis based on the user's inputs.
	 * It takes in the values for the country and years, and uses that to make a call to the 
	 * GetData class to retrieve the information, and then proceeds to do further calculations that we need.
	 * @param west
	 * @param country
	 * @param startDate
	 * @param endDate
	 * @param chartType
	 */
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new DefaultCategoryDataset();
		this.dataReport = new ArrayList<String>();

		GetData forestArea = new GetData("AG.LND.FRST.ZS", startDate - 1, endDate, country);
		forestArea.fetchData("World Bank");

		// ADDING THE DATA ON THE DATASET FOR SERIES 1
		// Average = (Sum of all years forest area percentages)/number of years
		double average = 0;
		double rest = 0;
		double sum = 0;

		for (int i = 0; i < forestArea.valueOfYear.size(); i++) {
			if (Double.isNaN(forestArea.valueOfYear.get(i))) {
				continue;
			} else {
				sum += forestArea.valueOfYear.get(i);
			}
		}
		average = sum / forestArea.year.size();
		rest = 100 - average;
		this.dataset.addValue(average, "Forest", "Area");
		this.dataset.addValue(rest, "Other", "Area");
		this.dataReport.add("Forest area average: " + average + "\n");
		this.dataReport.add("Other average: " + rest + "\n");
	}

	/**
	 * This method gets the dataset.
	 */
	public Object getDataSet() {
		return this.dataset;
	}

	/**
	 * This method gets the report.
	 */
	public ArrayList<String> getReport() {
		return this.dataReport;
	}

	/**
	 * This method gets the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method gets the ChartSystemView hashmap.
	 */
	public HashMap<String, Boolean> getChartSystemView() {
		return this.chartSystemView;
	}
}
