package AnalysisComponent;
/**
 * This class is an interface for our factory design pattern. Based on the user's selection, this class
 * will specify the required analysis type. 
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 *
 */

public class AnalysisFactory {
	AnalysisInterface analysis;
	/**
	 * This method will call the appropriate analysis object based on the user's choice.
	 * @param analysisType
	 * @return analysis object based on user input
	 */
	public AnalysisInterface createAnalysis(String analysisType) {
		
		// FIX THIS TO THROW AN EXCEPTION
		if(analysisType.equals("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution")) {
			analysis = new AnalysisOne();
			return analysis;
		}
		else if(analysisType.equals("Annual percentage change of PM2.5 air pollution & Forest area")) {
			analysis = new AnalysisTwo();
			return analysis;
		}
		else if(analysisType.equals("Ratio of CO2 emissions and GDP per capita")) {
			analysis = new AnalysisThree();
			return analysis;
		}
		else if(analysisType.equals("Average Forest area (as % of land area)")) {
			analysis = new AnalysisFour();
			return analysis;
		}
		else if(analysisType.equals("Government Expenditure: Education vs Other")) {
			analysis = new AnalysisFive();
			return analysis;
		}
		else if(analysisType.equals("Health Expenditure vs Hospital Beds/1000 People")) {
			analysis = new AnalysisSix();
			return analysis;
		}
		else if(analysisType.equals("Problems in accessing health care vs Infant mortality rate (per 1,000 live births)")) {
			analysis = new AnalysisSeven();
			return analysis;
		}
		else if(analysisType.equals("Annual percentage change Government expenditure on education and current health expenditure")){
			analysis = new AnalysisEight();
			return analysis;
		}
		return analysis;
	}
	
	
}
