package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
 

public class AnalysisFour implements AnalysisInterface {
	
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
	
	public AnalysisFour(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}
	
	public AnalysisFour() {
		// TODO Auto-generated constructor stub
	}

	public Object performAnalysis() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		GetData forestArea = new GetData("AG.LND.FRST.ZS", startDate, endDate, country);
		forestArea.fetchData();	

		//ADDING THE DATA ON THE DATASET FOR SERIES 1
		//Average = (Sum of all years forest area percentages)/number of years
		double sum = 0;
		double numOfYears = 0;
		for(int i = forestArea.year.size()-1; i > 0; i--) {
			sum += forestArea.valueOfYear.get(i-1);
			numOfYears++;			
		}
		double average = sum/numOfYears;
		double rest = 100-average;
		dataset.addValue(average, "Forest", "Area");
		dataset.addValue(rest, "Other", "Area");
		return dataset;
	}
	public Object getDataSet() {
		DefaultCategoryDataset datasetTemp = new DefaultCategoryDataset();
		return datasetTemp;
	}
	
	public boolean[] getCharts() {
		return this.charts;
	}

	public void update(boolean[] charts) {
		this.charts = charts;
	}

}
