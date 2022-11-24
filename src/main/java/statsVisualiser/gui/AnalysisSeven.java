package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisSeven implements AnalysisInterface{
	
	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};

	TimeSeriesCollection dataset; 
	
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Women Problems in Accessing Healthcare");
		GetData women_healthcare = new GetData("SH.ACS.MONY.Q1.ZS", startDate, endDate, country);
		women_healthcare.fetchData();
		
		TimeSeries series2 = new TimeSeries("Infant Mortality Rate"); 
		GetData infant_mortality = new GetData("SP.DYN.IMRT.IN", startDate, endDate, country);
		infant_mortality.fetchData();	
		
		for(int i = women_healthcare.year.size()-1; i > 1; i--) {
	        series1.add(new Year(women_healthcare.year.get(i)), women_healthcare.valueOfYear.get(i));
	    }
		this.dataset.addSeries(series1);
		
		for(int i = infant_mortality.year.size()-1; i > 1; i--) {
	        series2.add(new Year(infant_mortality.year.get(i)), infant_mortality.valueOfYear.get(i));
	    }
		this.dataset.addSeries(series2);
	}
	
	public Object getDataSet() {
		return this.dataset;
	}
	
	public boolean[] getCharts() {
		return this.charts;
	}

	public void updateCharts(boolean[] charts) {
		this.charts = charts;
	}	
}
