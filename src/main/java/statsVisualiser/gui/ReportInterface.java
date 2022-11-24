package statsVisualiser.gui;

public interface ReportInterface {
	
	public Object convertData(Object dataset, AnalysisInterface analysis);

	AnalysisInterface getAnalysisType(String analysisType);
}
