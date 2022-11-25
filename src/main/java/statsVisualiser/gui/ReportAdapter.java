package statsVisualiser.gui;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class ReportAdapter implements ReportInterface {	
	
	// WRITE THE REPORT
	public String writeReport(ArrayList<String> dataReport, String analysisType) {
		String reportMessage = analysisType + "\n";
		for(int i = 0; i < analysisType.length()/1.3; i++) {
			reportMessage += "=";
		}
		reportMessage += "\n";
		for(String s : dataReport) {
			reportMessage += s + "\n";
		}
		return reportMessage;
	}
}
//		String reportMessage = analysisType + "\n==============================\n";
//		
//		if(dataset instanceof TimeSeriesCollection) {
//			TimeSeriesCollection dataset3 = new TimeSeriesCollection();
//			dataset3 = (TimeSeriesCollection) dataset;
//			
////			JFreeChart chart = ChartFactory.createXYLineChart(analysisType, "Year", "", (XYDataset) dataset3, PlotOrientation.VERTICAL,
////					true, true, false);
////
////			XYPlot plot = chart.getXYPlot();
////			DateAxis domainAxis = new DateAxis("Year");
////			plot.setDomainAxis(domainAxis);
//			int counter = dataset3.getSeries(0).getItemCount();
//			int index = 0;
//			
//			for(int k = 0; k < dataset3.getSeriesCount(); k++) {
//				if(counter <= dataset3.getSeries(k).getItemCount()) {
//					counter = dataset3.getSeries(k).getItemCount();
//					index = k;
//				}
//			}
//			int currentYear = 0;
//			// FOR LOOP OVER THE YEARS
//			for(int j = 0; j < dataset3.getSeriesCount(); j++) {
//				// FOR LOOP OVER THE NUMBER OF TIME SERIES
//				// reportMessage += "\n";
//				TimeSeries series = dataset3.getSeries(j);
//			//	System.out.println( dataset3.getSeries(j).getItems().size());
//				for(int i = 0; i < counter; i++) {
//					if(i < dataset3.getSeries(index).getItems().size() && j == index) {
//						RegularTimePeriod yearObj = series.getTimePeriod(i);
//						String year = yearObj.toString();
//						currentYear++;
//						reportMessage += "Year " + year + " :\n"; 
//						for (int n = 0; n < dataset3.getSeriesCount(); n++) {
//							TimeSeries series2 = dataset3.getSeries(n);
//							for(int m = 0; m <= currentYear; m++) {
//								if(m < dataset3.getSeries(n).getItems().size() && m == currentYear) {
//									reportMessage += "blah \n";
//									// series2.getItems();
//								}
//							}
//						}
//						//reportMessage += "\n              name of timeseries" + " =>" + " val of timeseries";
//					} 
//				}
//			//	for(int l = 0; )
//				//reportMessage += "\n";
//			}
//			
////			if(dataset3.getSeriesCount() == 0) 
////				reportMessage = "Not retrieving the right dataset";
////			else
////				reportMessage = "Count: " + dataset3.getSeriesCount();
//			//	reportMessage += s.getItems().get(i);
//			
//		}
//		else if(dataset instanceof DefaultCategoryDataset)  {
//			DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
//			dataset4 = (DefaultCategoryDataset) dataset;
//			if(dataset4.getColumnCount() == 0) 
//				reportMessage = "Not retrieving the right dataset";
//			else
//				reportMessage = "Count: " + dataset4.getColumnCount();
//			for(int i = 0; i < dataset4.getRowCount(); i++) {
//				System.out.println(dataset4.getRowCount());
//			}
//		}
//		return reportMessage;
