package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class ReportAdapter implements ReportInterface {
	
	AnalysisInterface analysisData;
	
	@Override
	public AnalysisInterface getAnalysisType(String analysisType) {
		if(analysisType.equals("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution")) {
			analysisData = new AnalysisOne();
		}
		else if(analysisType.equals("Annual percentage change of PM2.5 air pollution & Forest area")) {
			analysisData = new AnalysisTwo();
			
		}
		else if(analysisType.equals("Ratio of CO2 emissions and GDP per capita")) {
			analysisData = new AnalysisThree();
			
		}
		else if(analysisType.equals("Average Forest area (as % of land area)")) {
			analysisData = new AnalysisFour();
			
		}
		else if(analysisType.equals("Government Expenditure: Education vs Other")) {
			analysisData = new AnalysisFive();
			
		}
		else if(analysisType.equals("Health Expenditure vs Hospital Beds/1000 People")) {
			analysisData = new AnalysisSix();
			
		}
		else if(analysisType.equals("Problems in accessing health care vs Infant mortality rate (per 1,000 live births)")) {
			analysisData = new AnalysisSeven();
			
		}
		else if(analysisType.equals("Annual percentage change Government expenditure on education and current health expenditure")) {
			analysisData = new AnalysisEight();
		}
		return analysisData;
	}
	
	// WRITE THE REPORT
	public String writeReport(Object dataset2, String reportMessage) {
		if(dataset2 instanceof TimeSeriesCollection) {
			TimeSeriesCollection dataset3 = new TimeSeriesCollection();
			dataset3 = (TimeSeriesCollection) dataset2;
			if(dataset3.getSeriesCount() == 0) 
				reportMessage = "Not retrieving the right dataset";
			else
				reportMessage = "Count: " + dataset3.getSeriesCount();
			//	reportMessage += s.getItems().get(i);
			
		}
		else if(dataset2 instanceof DefaultCategoryDataset)  {
			DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
			dataset4 = (DefaultCategoryDataset) dataset2;
			if(dataset4.getColumnCount() == 0) 
				reportMessage = "Not retrieving the right dataset";
			else
				reportMessage = "Count: " + dataset4.getColumnCount();
			for(int i = 0; i < dataset4.getRowCount(); i++) {
				System.out.println(dataset4.getRowCount());
			}
		}
		return reportMessage;
	}
	
	public Object convertData(Object dataset, AnalysisInterface analysis) {
		// TODO Auto-generated method stub
		return analysis.getDataSet();
	}

}
