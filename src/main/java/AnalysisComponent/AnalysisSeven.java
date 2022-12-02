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
 * This class is responsible for the 7th type of analysis that we have chosen to implement for this project.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class AnalysisSeven implements AnalysisInterface {

	public ArrayList<String> dataReport;
	public TimeSeriesCollection dataset;
	public HashMap<String, Boolean> chartSystemView = new HashMap<String, Boolean>();
	public String name = "Problems in accessing health care vs Infant mortality rate (per 1,000 live births)";

	/**
	 * Initializes the analysis object and puts the available graphs into the hashmap.
	 */
	public AnalysisSeven() {
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

		TimeSeries series1 = new TimeSeries("Women Problems in Accessing Healthcare");
		GetData women_healthcare = new GetData("SH.ACS.MONY.Q1.ZS", startDate, endDate, country);
		women_healthcare.fetchData();

		TimeSeries series2 = new TimeSeries("Infant Mortality Rate");
		GetData infant_mortality = new GetData("SP.DYN.IMRT.IN", startDate, endDate, country);
		infant_mortality.fetchData();

		for (int i = women_healthcare.year.size() - 1; i > 1; i--) {
			if (Double.isNaN(women_healthcare.valueOfYear.get(i))) {
				continue;
			} else {
				series1.add(new Year(women_healthcare.year.get(i)), women_healthcare.valueOfYear.get(i));
				this.dataReport.add("Women problems in accessing healthcare: " + women_healthcare.year.get(i) + " => "
						+ women_healthcare.valueOfYear.get(i));
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");

		for (int i = infant_mortality.year.size() - 1; i > 1; i--) {
			if (Double.isNaN(infant_mortality.valueOfYear.get(i))) {
				continue;
			} else {
				series2.add(new Year(infant_mortality.year.get(i)), infant_mortality.valueOfYear.get(i));
				this.dataReport.add("Infant mortality rate: " + infant_mortality.year.get(i) + " => "
						+ infant_mortality.valueOfYear.get(i));
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
