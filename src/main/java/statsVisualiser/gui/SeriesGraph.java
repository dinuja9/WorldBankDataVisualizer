package statsVisualiser.gui;
import javax.swing.JPanel;

public class SeriesGraph {
	
	private AnalysisInterface analysis;
	private RenderInterface render;
	private CountryInterface country;
	String analysisType = "";
	String chartType = "";
	
	public SeriesGraph(String analysisType, String chartType) {
		this.analysisType = analysisType;
		this.chartType = chartType;
	}
	
	public void setAnalysis(AnalysisInterface analysis) {
		this.analysis = analysis;
	}
	
	public void executeStrategy(JPanel west, String country, int startDate, int endDate, String chartType) {
		
		analysis.performAnalysis(west, country, startDate, endDate, chartType);
		Object dataset = analysis.getDataSet();
	//	DefaultCategoryDataset dataset2 = (DefaultCategoryDataset) this.analysis.performAnalysis();
	//	boolean [] charts = this.analysis.getCharts();
		RenderFactory factory = new RenderFactory();
		render = factory.createChart(this.chartType, dataset, west, this.analysisType);
		render.visualize();	
	}
	
}
