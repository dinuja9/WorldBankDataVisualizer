package statsVisualiser.gui;

import static org.junit.Assert.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import APIComponent.ApiCaller;
import APIComponent.ApiFactory;
import APIComponent.GetData;
import APIComponent.WorldBankApiCaller;
import AnalysisComponent.AnalysisEight;
import AnalysisComponent.AnalysisFactory;
import AnalysisComponent.AnalysisFive;
import AnalysisComponent.AnalysisFour;
import AnalysisComponent.AnalysisInterface;
import AnalysisComponent.AnalysisOne;
import AnalysisComponent.AnalysisSeven;
import AnalysisComponent.AnalysisSix;
import AnalysisComponent.AnalysisThree;
import AnalysisComponent.AnalysisTwo;
import GUIComponent.GUISingleton;
import GUIComponent.LoginChecker;
import GUIComponent.MainUI;

/**
 * This is the JUnit tester class
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */

public class PhaseTwoTesting {

	// TESTING ALL THE ANALYSIS CLASSES TO SEE IF THE PERFORM THE ANALYSIS AS EXPECTED
	@Test
	public void performAnalysisOneTest1() {
		AnalysisOne analysis = new AnalysisOne();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("CO2 emissions % change: 2015 => 0.0", report.get(0));
	}

	@Test
	public void performAnalysisTwoTest2() {
		AnalysisTwo analysis = new AnalysisTwo();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("air pollution % change: 2016 => -3.125", report.get(0));
	}

	@Test
	public void performAnalysisThreeTest3() {
		AnalysisThree analysis = new AnalysisThree();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("CO2 emissions/GDP per capita: 2015 => 3.197953309881676E-4", report.get(0));
	}

	@Test
	public void performAnalysisFourTest4() {
		AnalysisFour analysis = new AnalysisFour();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Forest area average: 54.285714285714285\n", report.get(0));
	}

	@Test
	public void performAnalysisFiveTest5() {
		AnalysisFive analysis = new AnalysisFive();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Education expenditure: 2.0\n", report.get(0));
	}

	@Test
	public void performAnalysisSixTest6() {
		AnalysisSix analysis = new AnalysisSix();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("health expidenture/hospital beds: 2019 => 2.524", report.get(0));
	}

	@Test
	public void performAnalysisSevenTest7() {
		AnalysisSeven analysis = new AnalysisSeven();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Women problems in accessing healthcare: 2015 => 0.0", report.get(0));
	}

	@Test
	public void performAnalysisEightTest8() {
		AnalysisEight analysis = new AnalysisEight();
		analysis.performAnalysis(new JPanel(), "AGO", 2015, 2020, "Report");
		ArrayList<String> report = analysis.getReport();
		assertEquals("Government education expidenture % change: 2015 => 50.0", report.get(0));
	}

	// TEST THE ANALYSIS FACTORY TO DETERMINE WHETHER THE CORRECT OBJECT TYPE WAS RETURNED
	@Test
	public void AnalysisFactoryTest9() {
		boolean type = false;
		AnalysisFactory factory = new AnalysisFactory();
		AnalysisInterface analysis = factory
				.createAnalysis("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution");
		type = analysis instanceof AnalysisOne;
		assertTrue(type);
	}

	// TEST API WORLD BANK TO SEE IF DATA WAS FETCHED CORRECTLY
	@Test
	public void WorldBankApiCallerTest10() {
		ApiFactory factory = new ApiFactory();
		ApiCaller api = factory.createAPI(2015, 2020, "AGO", "SE.XPD.TOTL.GD.ZS","World Bank");
		api.callAPI();
		ArrayList<Double> report = api.getValueOfYear();
		assertEquals(new BigDecimal(2.0), new BigDecimal(report.get(0)));
	}

	// TEST GET DATA CLASS TO SEE IF A NULL POINTER EXCEPTION IS THROWN FOR EMPTY ANALYSIS TYPE
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void GetDataTest11() {
		GetData data = new GetData("", 2015, 2020, "AGO");
		exception.expect(IndexOutOfBoundsException.class);
		data.fetchData("World Bank");
	}

	// TEST API FACTORY TO DETERMINE WHETHER THE CORRECT OBJECT TYPE WAS RETURNED
	@Test
	public void ApiFactoryTest12() {
		boolean type = false;
		ApiFactory factory = new ApiFactory();
		ApiCaller api = factory.createAPI(2015, 2020, "AGO", "SE.XPD.TOTL.GD.ZS","World Bank");
		type = api instanceof WorldBankApiCaller;
		assertTrue(type);
	}

	// TEST THE LOGINCHECKER TO SEE IF THE CORRECT USERNAME AND PASSWORD WAS ENTERED
	@Test
	public void LoginCheckerTest13() throws IOException, Exception {
		LoginChecker check = new LoginChecker("user", "12345");
		boolean value = check.checker();
		assertTrue(value);
	}

	// TEST TO MAKE SURE THERE IS ONLY ONE INSTANCE OF MAINUI
	@Test
	public void MainUIInstanceTest14() throws IOException, Exception {
		MainUI obj = MainUI.getInstance();
		MainUI obj2 = MainUI.getInstance();
		boolean value = obj.equals(obj2);
		assertTrue(value);
	}

	// TEST TO MAKE SURE THERE IS ONLY ONE INSTANCE OF GUISINGLETON
	@Test
	public void GUISingletonInstanceTest15() {
		GUISingleton GUIObj = GUISingleton.getInstance();
		GUISingleton GUIObj2 = GUISingleton.getInstance();
		boolean value = GUIObj.equals(GUIObj2);
		assertTrue(value);
	}
}
