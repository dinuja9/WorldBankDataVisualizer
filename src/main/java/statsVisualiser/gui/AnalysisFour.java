package statsVisualiser.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
 

public class AnalysisFour implements AnalysisInterface {
	
	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	public ArrayList<String> dataReport;
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};
	DefaultCategoryDataset dataset;
	
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		this.dataset = new DefaultCategoryDataset();
		this.dataReport = new ArrayList<String>();
		
		GetData forestArea = new GetData("AG.LND.FRST.ZS", startDate, endDate, country);
		forestArea.fetchData();	

		//ADDING THE DATA ON THE DATASET FOR SERIES 1
		//Average = (Sum of all years forest area percentages)/number of years
		double sum = 0;
		double numOfYears = 0;
		for(int i = forestArea.year.size()-1; i > 0; i--) {
			if(Double.isNaN(forestArea.valueOfYear.get(i))) {
				continue;
			}
			else {
				sum += forestArea.valueOfYear.get(i);
				numOfYears++;
			}
		}
		double average = sum/numOfYears;
		double rest = 100-average;
		
		this.dataset.addValue(average, "Forest", "Area");
		this.dataset.addValue(rest, "Other", "Area");
		this.dataReport.add("Forest area average: " +average+ "\n");
		this.dataReport.add("Other average: " +rest+ "\n");
	}
	public Object getDataSet() {
		DefaultCategoryDataset datasetTemp = new DefaultCategoryDataset();
		return datasetTemp;
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
