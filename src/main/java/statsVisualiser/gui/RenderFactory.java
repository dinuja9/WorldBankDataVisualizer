package statsVisualiser.gui;

import javax.swing.JPanel;

import org.jfree.data.time.TimeSeriesCollection;

public class RenderFactory {
	
	public RenderInterface createChart(String chartType, Object dataset, JPanel west, String analysisType) {
		if(chartType.equals("Line Chart")) {
			return new RenderLine(dataset, west, analysisType);
		}
		else if(chartType.equals("Pie Chart")) {
			return new RenderPie(dataset, west, analysisType);
		}
		else if(chartType.equals("Scatter Chart")) {
			return new RenderScatter(dataset, west, analysisType);
		}
		else if(chartType.equals("Bar Chart")) {
			return new RenderBar(dataset, west, analysisType);
		}
		else {
			return new RenderReport(dataset, west, analysisType);
		}
	}
}
