package statsVisualiser.gui;

import java.util.ArrayList;

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
	public ArrayList<String> dataReport;
	TimeSeriesCollection dataset; 
	
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new TimeSeriesCollection();
		this.dataReport = new ArrayList<String>();
		
		TimeSeries series1 = new TimeSeries("Women Problems in Accessing Healthcare");
		GetData women_healthcare = new GetData("SH.ACS.MONY.Q1.ZS", startDate, endDate, country);
		women_healthcare.fetchData();
		
		TimeSeries series2 = new TimeSeries("Infant Mortality Rate"); 
		GetData infant_mortality = new GetData("SP.DYN.IMRT.IN", startDate, endDate, country);
		infant_mortality.fetchData();	
		
		for(int i = women_healthcare.year.size()-1; i > 1; i--) {
			if(Double.isNaN(women_healthcare.valueOfYear.get(i))) {
				continue;
			}
			else {
				series1.add(new Year(women_healthcare.year.get(i)), women_healthcare.valueOfYear.get(i));
				this.dataReport.add("Women problems in accessing healthcare: " + women_healthcare.year.get(i) + " => " + women_healthcare.valueOfYear.get(i));
			}
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");
		
		for(int i = infant_mortality.year.size()-1; i > 1; i--) {
			if(Double.isNaN(infant_mortality.valueOfYear.get(i))) {
				continue;
			}
			else {
				 series2.add(new Year(infant_mortality.year.get(i)), infant_mortality.valueOfYear.get(i));
				 this.dataReport.add("Infant mortality rate: " + infant_mortality.year.get(i) + " => " + infant_mortality.valueOfYear.get(i));
			}
		}
		this.dataset.addSeries(series2);
		this.dataReport.add("\n");
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

	@Override
	public ArrayList<String> getReport() {
		// TODO Auto-generated method stub
		return this.dataReport;
	}	
}
