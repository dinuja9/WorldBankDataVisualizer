package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisTwo implements AnalysisInterface {
	
	boolean lineChart = false;
	boolean scatterChart = false;
	boolean report = false;
	
	boolean [] charts = {false, lineChart, false, scatterChart, report};
	
	JPanel west; 
	String country; 
	int startDate; 
	int endDate; 
	String chart;
	
	public AnalysisTwo(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}

	public AnalysisTwo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object performAnalysis() {
		// TODO Auto-generated method stub
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", (startDate)-1, endDate, country);
		airPolution.fetchData();
		
		TimeSeries series2 = new TimeSeries("Annual % change of forest area"); 
		GetData forestArea = new GetData("AG.LND.FRST.ZS", (startDate)-1, endDate, country);
		forestArea.fetchData();	
		
		// ADDING THE DATA ON THE DATASET FOR SERIES 1 AND 2
		// percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100
		
		for(int i = 1; i < airPolution.year.size(); i++){
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series1.add(new Year(airPolution.year.get(i)), percentChange);
			}
			System.out.println("air pol =>" + airPolution.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		dataset.addSeries(series1);
		
		for(int i = 1; i < forestArea.year.size(); i++) {
			double CurrentYearValue = forestArea.valueOfYear.get(i);
			double LastYearValue = forestArea.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series2.add(new Year(forestArea.year.get(i)), percentChange);
			}
			System.out.println("forest area =>" + forestArea.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		dataset.addSeries(series2);
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
