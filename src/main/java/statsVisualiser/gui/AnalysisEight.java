package statsVisualiser.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisEight implements AnalysisInterface{

	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};
	TimeSeriesCollection dataset;
	public ArrayList<String> dataReport;

	public void performAnalysis (JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new TimeSeriesCollection();
		this.dataReport = new  ArrayList<String>();
		
		TimeSeries series1 = new TimeSeries("Government expenditure on education, total (% of GDP)");
		GetData govt_exp_edu = new GetData("SE.XPD.TOTL.GD.ZS", startDate, endDate, country);
		govt_exp_edu.fetchData();
		
		TimeSeries series2 = new TimeSeries("Current health expenditure (% of GDP)"); 
		GetData health_exp = new GetData("SH.XPD.CHEX.GD.ZS", startDate, endDate, country);
		health_exp.fetchData();
		
		for(int i = govt_exp_edu.year.size()-1; i > 1; i--) {
	        
			double CurrentYearValue = govt_exp_edu.valueOfYear.get(i);
	        double LastYearValue = govt_exp_edu.valueOfYear.get(i-1);
	        double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
	        
	        if( Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
	            continue;
	        }
	        else {
	        	series1.add(new Year(govt_exp_edu.year.get(i)), percentChange);
				this.dataReport.add("Government education expidenture % change: " + govt_exp_edu.year.get(i) + " => " + percentChange);
	        }
	        System.out.println(percentChange);
		}
		this.dataset.addSeries(series1);
		this.dataReport.add("\n");
		
		
		for(int i = health_exp.year.size()-1; i > 1; i--) {
	        
			double CurrentYearValue = health_exp.valueOfYear.get(i);
	        double LastYearValue = health_exp.valueOfYear.get(i-1);
	        double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
	        
	        if( Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
	            continue;
	        }
	        else {
	        	series2.add(new Year(health_exp.year.get(i)), percentChange);
	        	this.dataReport.add("Current health expidenture % change: " + health_exp.year.get(i) + " => " + percentChange);
	        }

		}
		this.dataset.addSeries(series2);
		this.dataReport.add("\n");
	}
	
	@Override
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
