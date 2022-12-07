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
 * This class is responsible for the 1st type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class AnalysisOne implements AnalysisInterface {

	public TimeSeriesCollection dataset;
	public ArrayList<String> dataReport;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisOne() {
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
		GetData CO2Emissions = new GetData("EN.ATM.CO2E.PC", startDate - 1, endDate, country);
		CO2Emissions.fetchData("World Bank");

		TimeSeries series2 = new TimeSeries("Annual % change of energy use");
		GetData NRG = new GetData("EG.USE.PCAP.KG.OE", startDate - 1, endDate, country);
		NRG.fetchData("World Bank");

		TimeSeries series3 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", startDate - 1, endDate, country);
		airPolution.fetchData("World Bank");

		// ADDING THE DATA ON THE DATASET FOR SERIES 1 TO 3
		// percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100
		for (int i = 1; i < CO2Emissions.year.size(); i++) {
			double CurrentYearValue = CO2Emissions.valueOfYear.get(i);
			double LastYearValue = CO2Emissions.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;
			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series1.add(new Year(CO2Emissions.year.get(i)), percentChange);
				this.dataReport.add("CO2 emissions % change: " + CO2Emissions.year.get(i) + " => " + percentChange);
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");

		for (int i = 1; i < NRG.year.size(); i++) {
			double CurrentYearValue = NRG.valueOfYear.get(i);
			double LastYearValue = NRG.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;
			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series2.add(new Year(NRG.year.get(i)), percentChange);
				this.dataReport.add("Energy use % change: " + NRG.year.get(i) + " => " + percentChange);
			}
		}
		this.dataset.addSeries(series2);
		this.dataReport.add("\n");

		for (int i = 1; i < airPolution.year.size(); i++) {
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;
			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series3.add(new Year(airPolution.year.get(i)), percentChange);
				this.dataReport.add("airPolution: " + airPolution.year.get(i) + " => " + percentChange);
			}
		}
		this.dataset.addSeries(series3);
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
