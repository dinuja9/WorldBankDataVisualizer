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
 * This class is responsible for the 3rd type of analysis that we have chosen to implement for this project.
 * 
 */
public class AnalysisThree implements AnalysisInterface {

	public TimeSeriesCollection dataset;
	public ArrayList<String> dataReport;
	public String name = "Ratio of CO2 emissions and GDP per capita";
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisThree() {
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
	public void performAnalysis(JPanel west, String country, int startDate, int endDate) {
		this.dataset = new TimeSeriesCollection();
		this.dataReport = new ArrayList<String>();

		TimeSeries series1 = new TimeSeries("Annual % change of CO2 emissions");
		GetData CO2Emissions = new GetData("EN.ATM.CO2E.PC", startDate, endDate, country);
		CO2Emissions.fetchData("World Bank");
		GetData GDP = new GetData("NY.GDP.PCAP.CD", startDate, endDate, country);
		GDP.fetchData("World Bank");

		for (int i = CO2Emissions.year.size() - 1; i > 1; i--) {
			if (Double.isNaN(CO2Emissions.valueOfYear.get(i))) {
				continue;
			} else {
				double ratio = CO2Emissions.valueOfYear.get(i) / GDP.valueOfYear.get(i);
				series1.add(new Year(CO2Emissions.year.get(i)), ratio);
				this.dataReport.add("CO2 emissions/GDP per capita: " + CO2Emissions.year.get(i) + " => " + ratio);
			}
		}
		dataset.addSeries(series1);
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
