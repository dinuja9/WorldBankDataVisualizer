package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class AnalysisOne implements AnalysisInterface{
	
	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	public TimeSeriesCollection dataset;
	
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};

	@Override
	public void performAnalysis (JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Annual % change of CO2 emissions"); 
		GetData CO2Emissions = new GetData("EN.ATM.CO2E.PC", startDate-1, endDate, country);
		CO2Emissions.fetchData();

		TimeSeries series2 = new TimeSeries("Annual % change of energy use"); 
		GetData NRG = new GetData("EG.USE.PCAP.KG.OE", startDate-1, endDate, country);
		NRG.fetchData();
		
		TimeSeries series3 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", startDate-1, endDate, country);
		airPolution.fetchData();

		// ADDING THE DATA ON THE DATASET FOR SERIES 1 TO 3
		//percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100
		for(int i = 1; i < CO2Emissions.year.size(); i++) {
			double CurrentYearValue = CO2Emissions.valueOfYear.get(i);
			double LastYearValue = CO2Emissions.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange)) {
				continue;
			}
			else {
				series1.add(new Year(CO2Emissions.year.get(i)), percentChange);
				System.out.println("CO2 emissions =>" + CO2Emissions.year.get(i) + " : " + percentChange);
			}
		}
		this.dataset.addSeries(series1);
		
		for(int i = 1; i < NRG.year.size(); i++) {
			double CurrentYearValue = NRG.valueOfYear.get(i-1);
			double LastYearValue = NRG.valueOfYear.get(i);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange)) {
				continue;
			}
			else {
			series2.add(new Year(NRG.year.get(i)), percentChange);
			System.out.println("NRG" + percentChange + "=>" + CurrentYearValue + " : " + LastYearValue);
			}
		}
		this.dataset.addSeries(series2);
		
		for(int i = 1; i < airPolution.year.size(); i++) {
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series3.add(new Year(airPolution.year.get(i)), percentChange);
			}
			System.out.println("air pol =>" + airPolution.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		this.dataset.addSeries(series3);
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
