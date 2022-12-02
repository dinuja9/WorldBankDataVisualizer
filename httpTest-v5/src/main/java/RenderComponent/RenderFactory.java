package RenderComponent;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This is the render factory class for our graphs. This class will call the requested graph class based on the user's choice.    
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class RenderFactory {
	
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
			return new RenderReport(west, analysisType, dataReport);
		}
	}
}
