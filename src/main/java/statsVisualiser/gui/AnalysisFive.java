package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class AnalysisFive implements AnalysisInterface {

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
	
	public AnalysisFive(JPanel west, String country, int startDate, int endDate, String chart) {
		this.west = west;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chart = chart;
	}
	
	public AnalysisFive() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object performAnalysis() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		GetData expenditure = new GetData("SE.XPD.TOTL.GD.ZS", this.startDate, this.endDate, this.country);
		expenditure.fetchData();

		double education = 0.0;
		double sum = 0.0;
		double otherExp = 0.0;
		for (int i = 0; i < expenditure.valueOfYear.size(); i++) {
			sum += expenditure.valueOfYear.get(i);
		}
		education = sum / (double) expenditure.valueOfYear.size();
		otherExp = 100.0 - education;

		dataset.addValue(education, "Education", "Expenditure");
		dataset.addValue(otherExp, "Other", "Expenditure");
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
