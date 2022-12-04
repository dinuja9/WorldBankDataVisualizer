package AnalysisComponent;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import APIComponent.GetData;

/**
 * 
 * This class is responsible for the 6th type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class AnalysisSix implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public TimeSeriesCollection dataset;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Health Expenditure vs Hospital Beds/1000 People";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisSix() {
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
		this.dataset = new TimeSeriesCollection();
		this.dataReport = new ArrayList<String>();

		TimeSeries series1 = new TimeSeries("Hospital Beds vs Health Expenditure");
		GetData beds = new GetData("SH.MED.BEDS.ZS", 2001, 2020, "can");
		GetData health = new GetData("SH.XPD.CHEX.PC.CD", 2001, 2020, "can");
		beds.fetchData("World Bank");
		health.fetchData("World Bank");
		double hSpent = 0.0;

		for (int i = 0; i < beds.valueOfYear.size(); i++) {
			hSpent = health.valueOfYear.get(i) / 1000.0;
			double ratio = hSpent / beds.valueOfYear.get(i);
			if (Double.isNaN(ratio)) {
				continue;
			} else {
				series1.add(new Year(beds.year.get(i)), ratio);
				this.dataReport.add("health expidenture/hospital beds: " + beds.year.get(i) + " => " + ratio);
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");
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
