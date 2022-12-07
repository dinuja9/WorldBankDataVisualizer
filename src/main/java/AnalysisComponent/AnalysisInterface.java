package AnalysisComponent;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * This class is the interface for all the analysis classes. It gives them the required
 * methods needed to perform the analysis. 
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */

public interface AnalysisInterface {
	public void performAnalysis(JPanel west, String country, int startDate, int endDate);
	public Object getDataSet();
	public ArrayList<String> getReport();
	public String getName();
	public HashMap<String, Boolean> getChartSystemView();
}
