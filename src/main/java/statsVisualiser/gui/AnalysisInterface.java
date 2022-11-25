package statsVisualiser.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

public interface AnalysisInterface {
	public void performAnalysis(JPanel west, String country, int startDate, int endDate, String chartType);
	public boolean[] getCharts();
	public void updateCharts(boolean[] charts);
	public Object getDataSet();
	public ArrayList<String> getReport();
}
