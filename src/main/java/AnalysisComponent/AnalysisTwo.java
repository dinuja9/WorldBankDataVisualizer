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
 * This class is responsible for the 2nd type of analysis that we have chosen to implement for this project.
 * 
 */
public class AnalysisTwo implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public TimeSeriesCollection dataset;
	public String name = "Annual percentage change of PM2.5 air pollution & Forest area";
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisTwo() {
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

		TimeSeries series1 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", (startDate) - 1, endDate, country);
		airPolution.fetchData("World Bank");

		TimeSeries series2 = new TimeSeries("Annual % change of forest area");
		GetData forestArea = new GetData("AG.LND.FRST.ZS", (startDate) - 1, endDate, country);
		forestArea.fetchData("World Bank");

		// ADDING THE DATA ON THE DATASET FOR SERIES 1 AND 2
		// percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100

		for (int i = 1; i < airPolution.year.size(); i++) {
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;
			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series1.add(new Year(airPolution.year.get(i)), percentChange);
				this.dataReport.add("air pollution % change: " + airPolution.year.get(i) + " => " + percentChange);
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");

		for (int i = 1; i < forestArea.year.size(); i++) {
			double CurrentYearValue = forestArea.valueOfYear.get(i);
			double LastYearValue = forestArea.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;
			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series2.add(new Year(forestArea.year.get(i)), percentChange);
				this.dataReport.add("forest area % change: " + forestArea.year.get(i) + " => " + percentChange);
			}
		}
		this.dataset.addSeries(series2);
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
