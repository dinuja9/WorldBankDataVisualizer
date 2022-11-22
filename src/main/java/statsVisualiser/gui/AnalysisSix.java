package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisSix implements AnalysisInterface{
	
	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};

	JPanel west; 
	String country; 
	int startDate; 
	int endDate; 
	String chart;
	
	public AnalysisSix(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}
	
	public AnalysisSix() {
		// TODO Auto-generated constructor stub
	}

	public Object performAnalysis() {
		// TODO Auto-generated method stub
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries series1 = new TimeSeries("Hospital Beds vs Health Expenditure");
		GetData beds = new GetData("SH.MED.BEDS.ZS", 2001, 2020, "can");
		GetData health = new GetData("SH.XPD.CHEX.PC.CD", 2001, 2020, "can");
		beds.fetchData();
		health.fetchData();
		double hSpent = 0.0;
		
		for(int i = 0; i < beds.valueOfYear.size(); i++) {
				hSpent = health.valueOfYear.get(i) / 1000.0;
				double ratio = hSpent / beds.valueOfYear.get(i);
				series1.add(new Year(beds.year.get(i)), ratio);
			}
		dataset.addSeries(series1);
		return dataset;
	}

	public Object getDataSet() {
		TimeSeriesCollection datasetTemp = new TimeSeriesCollection();
		return datasetTemp;
	}

	public boolean[] getCharts() {
		return this.charts;
	}

	public void update(boolean[] charts) {
		this.charts = charts;
	}

}
