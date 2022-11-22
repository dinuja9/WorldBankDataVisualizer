package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisThree implements AnalysisInterface{

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
	
	public AnalysisThree(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}
	
	public AnalysisThree() {
		// TODO Auto-generated constructor stub
	}

	public Object performAnalysis() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
				
		TimeSeries series1 = new TimeSeries("Annual % change of CO2 emissions"); 
		GetData C02perCapita = new GetData("EN.ATM.CO2E.PC", this.startDate, this.endDate, this.country);
		C02perCapita.fetchData();	
		GetData GDPperCapita = new GetData("NY.GDP.PCAP.CD", this.startDate, this.endDate, this.country);
		GDPperCapita.fetchData();
				
		for(int i = C02perCapita.year.size()-1; i > 1; i--) {
            series1.add(new Year(C02perCapita.year.get(i)), C02perCapita.valueOfYear.get(i)/GDPperCapita.valueOfYear.get(i));
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
