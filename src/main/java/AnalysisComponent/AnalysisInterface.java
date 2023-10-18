package AnalysisComponent;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * This class is the interface for all the analysis classes. It gives them the required
 * methods needed to perform the analysis. 
 *
 */

public interface AnalysisInterface {
	public void performAnalysis(JPanel west, String country, int startDate, int endDate);
	public Object getDataSet();
	public ArrayList<String> getReport();
	public String getName();
	public HashMap<String, Boolean> getChartSystemView();
}
