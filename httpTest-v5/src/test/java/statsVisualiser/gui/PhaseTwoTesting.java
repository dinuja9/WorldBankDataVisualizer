package statsVisualiser.gui;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Test;

import AnalysisComponent.AnalysisEight;
import AnalysisComponent.AnalysisFive;
import AnalysisComponent.AnalysisFour;
import AnalysisComponent.AnalysisOne;
import AnalysisComponent.AnalysisSeven;
import AnalysisComponent.AnalysisSix;
import AnalysisComponent.AnalysisThree;
import AnalysisComponent.AnalysisTwo;

public class PhaseTwoTesting {

	// TESTING ALL THE ANALYSIS CLASSES
	@Test
	public void performAnalsisOne() {
		AnalysisOne analysis = new AnalysisOne();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("CO2 emissions % change: 2015 => 0.0", report.get(0));
	}
	@Test
	public void performAnalsisTwo() {
		AnalysisTwo analysis = new AnalysisTwo();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("air pollution % change: 2016 => -3.125", report.get(0));
	}
	@Test
	public void performAnalsisThree() {
		AnalysisThree analysis = new AnalysisThree();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("CO2 emissions/GDP per capita: 2015 => 3.197953309881676E-4", report.get(0));
	}
	@Test
	public void performAnalsisFour() {
		AnalysisFour analysis = new AnalysisFour();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Forest area average: 54.285714285714285\n", report.get(0));
	}
	@Test
	public void performAnalsisFive() {
		AnalysisFive analysis = new AnalysisFive();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Education expenditure: 2.0\n", report.get(0));
	}
	@Test
	public void performAnalsisSix() {
		AnalysisSix analysis = new AnalysisSix();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("health expidenture/hospital beds: 2019 => 2.524", report.get(0));
	}
	@Test
	public void performAnalsisSeven() {
		AnalysisSeven analysis = new AnalysisSeven();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Women problems in accessing healthcare: 2015 => 0.0", report.get(0));
	}
	@Test
	public void performAnalsisEight() {
		AnalysisEight analysis = new AnalysisEight();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Government education expidenture % change: 2015 => 50.0", report.get(0));
	}
	// TEST API WORLD BANK
//	@Test
//	public void WorldBankApiCallerTest() {
//		WorldBankApiCaller api = new WorldBankApiCaller();
//		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
//		ArrayList<String> report = analysis.getReport();
//		System.out.println(report.get(0));
//		String url = "http://api.worldbank.org/v2/country/AGO/indicator/"+analysis+"?date="+2015+":"+2020+"&format=json";
//		assertEquals("Government education expidenture % change: 2015 => 50.0", report.get(0));
//	}
}
