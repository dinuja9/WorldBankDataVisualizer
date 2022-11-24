package statsVisualiser.gui;

public class AnalysisFactory {

	
	public AnalysisInterface createAnalysis(String analysisType) {
		
		// FIX THIS TO THROW AN EXCEPTION
		if(analysisType.equals("Select")) {
			System.out.println("Select an analysis!!!");
			return null;
		}
		else if(analysisType.equals("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution")) {
			return new AnalysisOne();
		}
		else if(analysisType.equals("Annual percentage change of PM2.5 air pollution & Forest area")) {
			return new AnalysisTwo();
		}
		else if(analysisType.equals("Ratio of CO2 emissions and GDP per capita")) {
			return new AnalysisThree();
		}
		else if(analysisType.equals("Average Forest area (as % of land area)")) {
			return new AnalysisFour();
		}
		else if(analysisType.equals("Government Expenditure: Education vs Other")) {
			return new AnalysisFive();
		}
		else if(analysisType.equals("Health Expenditure vs Hospital Beds/1000 People")) {
			return new AnalysisSix();
		}
		else if(analysisType.equals("Problems in accessing health care vs Infant mortality rate (per 1,000 live births)")) {
			return new AnalysisSeven();
		}
		else {
			return new AnalysisEight();
		}
	}
	
	
}
