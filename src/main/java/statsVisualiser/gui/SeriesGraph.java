package statsVisualiser.gui;

import javax.swing.JPanel;
import org.jfree.data.time.TimeSeriesCollection;


public class SeriesGraph {
	
	public SeriesGraph(JPanel west, String analysisType, String country, int startDate, int endDate, String chartType) {
		if(analysisType.equals("annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution")) {
			create3Series(west, country, startDate, endDate, chartType);
		}
		else if(analysisType.equals("annual percentage change of PM2.5 air pollution & Forest area")) {
			create2Series(west, country, startDate, endDate, chartType);
		}
	}
	
	private void create2Series(JPanel west, String country, int startDate, int endDate, String chartType) {
		AnalysisTwo series2 = new AnalysisTwo(west, country, startDate, endDate, chartType);
		TimeSeriesCollection dataset = series2.performAnalysis();
		Render render = new Render(dataset, west);
		render.visualize();
		
	}

	private void create3Series(JPanel west, String country, int startDate, int endDate, String chartType)  {
		AnalysisOne series3 = new AnalysisOne(west, country, startDate, endDate, chartType);
		TimeSeriesCollection dataset = series3.performAnalysis();
		Render render = new Render(dataset, west);
		render.visualize();
	}
	
}
