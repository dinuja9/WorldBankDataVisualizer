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
 * This class is responsible for the 8th type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */

public class AnalysisEight implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public TimeSeriesCollection dataset;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Annual percentage change Government expenditure on education and current health expenditure";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisEight() {
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

		TimeSeries series1 = new TimeSeries("Government expenditure on education, total (% of GDP)");
		GetData govt_exp_edu = new GetData("SE.XPD.TOTL.GD.ZS", startDate, endDate, country);
		govt_exp_edu.fetchData("World Bank");

		TimeSeries series2 = new TimeSeries("Current health expenditure (% of GDP)");
		GetData health_exp = new GetData("SH.XPD.CHEX.GD.ZS", startDate, endDate, country);
		health_exp.fetchData("World Bank");

		for (int i = govt_exp_edu.year.size() - 1; i > 1; i--) {

			double CurrentYearValue = govt_exp_edu.valueOfYear.get(i);
			double LastYearValue = govt_exp_edu.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;

			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series1.add(new Year(govt_exp_edu.year.get(i)), percentChange);
				this.dataReport.add("Government education expidenture % change: " + govt_exp_edu.year.get(i) + " => "
						+ percentChange);
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");

		for (int i = health_exp.year.size() - 1; i > 1; i--) {

			double CurrentYearValue = health_exp.valueOfYear.get(i);
			double LastYearValue = health_exp.valueOfYear.get(i - 1);
			double percentChange = (CurrentYearValue - LastYearValue) / LastYearValue * 100;

			if (Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue == 0.0) {
				continue;
			} else {
				series2.add(new Year(health_exp.year.get(i)), percentChange);
				this.dataReport
						.add("Current health expidenture % change: " + health_exp.year.get(i) + " => " + percentChange);
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
