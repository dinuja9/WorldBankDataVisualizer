package statsVisualiser.gui;

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

	JPanel west; 
	String country; 
	int startDate; 
	int endDate; 
	String chart;
	
	public AnalysisEight(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}
	
	public AnalysisEight() {
		// TODO Auto-generated constructor stub
	}

	public Object performAnalysis() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Government expenditure on education, total (% of GDP)");
		GetData govt_exp_edu = new GetData("SE.XPD.TOTL.GD.ZS", this.startDate, this.endDate, this.country);
		govt_exp_edu.fetchData();
		
		TimeSeries series2 = new TimeSeries("Current health expenditure (% of GDP)"); 
		GetData health_exp = new GetData("SH.XPD.CHEX.GD.ZS", this.startDate, this.endDate, this.country);
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
	        }
	        System.out.println(percentChange);
		}
		dataset.addSeries(series1);
		
		
		for(int i = health_exp.year.size()-1; i > 1; i--) {
	        
			double CurrentYearValue = health_exp.valueOfYear.get(i);
	        double LastYearValue = health_exp.valueOfYear.get(i-1);
	        double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
	        
	        if( Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
	            continue;
	        }
	        else {
	        series2.add(new Year(health_exp.year.get(i)), percentChange);
	        }

		}
		dataset.addSeries(series2);
		return dataset;
	}
	
	@Override
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
