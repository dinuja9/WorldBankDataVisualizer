package statsVisualiser.gui;

import java.util.ArrayList;

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
	public ArrayList<String> dataReport;
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};

	TimeSeriesCollection dataset;

	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new TimeSeriesCollection();
		this.dataReport = new ArrayList<String>();
		
		TimeSeries series1 = new TimeSeries("Annual % change of CO2 emissions"); 
		GetData CO2Emissions = new GetData("EN.ATM.CO2E.PC", startDate, endDate, country);
		CO2Emissions.fetchData();	
		GetData GDP = new GetData("NY.GDP.PCAP.CD", startDate, endDate, country);
		GDP.fetchData();
				
		for(int i = CO2Emissions.year.size()-1; i > 1; i--) {
			if(Double.isNaN(CO2Emissions.valueOfYear.get(i))) {
				continue;
			}
			else {
				double ratio = CO2Emissions.valueOfYear.get(i)/GDP.valueOfYear.get(i);
				series1.add(new Year(CO2Emissions.year.get(i)), ratio);
				this.dataReport.add("CO2 emissions/GDP per capita: " + CO2Emissions.year.get(i) + " => " + ratio);
			}
		}
		dataset.addSeries(series1);
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
