package AnalysisComponent;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

import APIComponent.GetData;

/**
 * 
 * This class is responsible for the 5th type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class AnalysisFive implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public DefaultCategoryDataset dataset;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Government Expenditure: Education vs Other";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisFive() {
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
		GetData expenditure = new GetData("SE.XPD.TOTL.GD.ZS", startDate, endDate, country);
		expenditure.fetchData();

		double education = 0.0;
		double sum = 0.0;
		double otherExp = 0.0;

		for (int i = 0; i < expenditure.valueOfYear.size(); i++) {
			if (Double.isNaN(expenditure.valueOfYear.get(i))) {
				continue;
			} else {
				sum += expenditure.valueOfYear.get(i);
			}
		}

		education = sum / (double) expenditure.valueOfYear.size();
		otherExp = 100.0 - education;
		this.dataset.addValue(education, "Education", "Expenditure");
		this.dataset.addValue(otherExp, "Other", "Expenditure");
		this.dataReport.add("Education expenditure: " + education + "\n");
		this.dataReport.add("Other expenditure: " + otherExp + "\n");
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
