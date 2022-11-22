package statsVisualiser.gui;

public interface AnalysisInterface {
	public Object performAnalysis();
	public boolean[] getCharts();
	public void update(boolean[] charts);
	public Object getDataSet();

}
