package statsVisualiser.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class AnalysisFive implements AnalysisInterface {

	boolean pieChart = false;
	boolean lineChart = false;
	boolean barChart = false;
	boolean scatterChart = false;
	boolean report = false;
	public ArrayList<String> dataReport;
	boolean [] charts = {pieChart, lineChart, barChart, scatterChart, report};

	DefaultCategoryDataset dataset;

	@Override
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType) {
		
		this.dataset = new DefaultCategoryDataset();
		this.dataReport = new ArrayList<String>();
		GetData expenditure = new GetData("SE.XPD.TOTL.GD.ZS", startDate, endDate, country);
		expenditure.fetchData();

		double education = 0.0;
		double sum = 0.0;
		double otherExp = 0.0;
		
		for (int i = 0; i < expenditure.valueOfYear.size(); i++) {
			if(Double.isNaN(expenditure.valueOfYear.get(i))) {
				continue;
			}
			else {
				sum += expenditure.valueOfYear.get(i);
			}
		}
		
		education = sum / (double) expenditure.valueOfYear.size();
		otherExp = 100.0 - education;
		this.dataset.addValue(education, "Education", "Expenditure");
		this.dataset.addValue(otherExp, "Other", "Expenditure");
		this.dataReport.add("Education expenditure: " + education + "\n");
		this.dataReport.add("Other expenditure: " + otherExp + "\n");
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
