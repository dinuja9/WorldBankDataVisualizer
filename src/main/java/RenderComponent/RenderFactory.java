package RenderComponent;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This is the render factory class for our graphs. This class will call the requested graph class based on the user's choice.    
 */
public class RenderFactory {
	RenderInterface render;
	/**
	 * This method checks to see which graph has been selected in the chartType variable, and then proceeds to call its respective graph class. 
	 * @param chartType
	 * @param dataset
	 * @param west
	 * @param analysisType
	 * @param dataReport
	 * @return a new graph based on the user's input.
	 */
	public RenderInterface createChart(String chartType, Object dataset, JPanel west, String analysisType, ArrayList<String> dataReport) {
		if(chartType.equals("Line Chart")) {
			render = new RenderLine(dataset, west, analysisType);
			return render;
		}
		else if(chartType.equals("Pie Chart")) {
			render = new RenderPie(dataset, west, analysisType);
			return render;
		}
		else if(chartType.equals("Scatter Chart")) {
			render = new RenderScatter(dataset, west, analysisType);
			return render;
		}
		else if(chartType.equals("Bar Chart")) {
			render = new RenderBar(dataset, west, analysisType);
			return render;
		}
		else {
			render = new RenderReport(west, analysisType, dataReport);
			return render;
		}
	}
}
