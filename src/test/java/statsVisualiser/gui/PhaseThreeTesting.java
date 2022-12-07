package statsVisualiser.gui;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import AnalysisComponent.AnalysisInterface;
import GUIComponent.LoginChecker;
import GUIComponent.MainUI;
import GUIComponent.ObserverInterface;


public class PhaseThreeTesting {
	public MainUI mainGUI;
/////////////////////////// USE CASE 1 TESTING ////////////////////////////////////////////

	// USERNAME WRONG, PASSWORD RIGHT
	@Test
	public void LoginTest1() throws IOException, Exception {
		LoginChecker check = new LoginChecker("us3r", "12345");
		boolean value = check.checker();
		assertFalse(value);
	}

	// USERNAME RIGHT, PASSWORD WRONG
	@Test
	public void LoginTest2() throws IOException, Exception {
		LoginChecker check = new LoginChecker("user", "12E45");
		boolean value = check.checker();
		assertFalse(value);
	}

	// USERNAME RIGHT, PASSWORD RIGHT
	@Test
	public void LoginTest3() throws IOException, Exception {
		LoginChecker check = new LoginChecker("user", "12345");
		boolean value = check.checker();
		assertTrue(value);
	}

	// USERNAME EMPTY, PASSWORD POPULATED
	@Test
	public void LoginTest4() throws IOException, Exception {
		LoginChecker check = new LoginChecker("", "12345");
		boolean value = check.checker();
		assertFalse(value);
	}

	// USERNAME POPULATE, PASSWORD EMPTY
	@Test
	public void LoginTest5() throws IOException, Exception {
		LoginChecker check = new LoginChecker("user", "");
		boolean value = check.checker();
		assertFalse(value);
	}

/////////////////////////// USE CASE 2 TESTING ////////////////////////////////////////////

	// COUNTRY IS NOT IN THE COUNTRY DROP DOWN MENU
	@Test
	
	public void CountryTest6() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getCountriesNames().contains("Africa"))) {
			value = true;
		}
		assertTrue(value);
	}

	// COUNTRY IS IN THE COUNTRY DROP DOWN MENU
	@Test
	public void CountryTest7() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if ((mainGUI.getCountriesNames().contains("Andorra"))) {
			value = true;
		}
		assertTrue(value);
	}

	// COUNTRY IS IN THE COUNTRY DROP DOWN MENU
	@Test
	public void CountryTest8() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if ((mainGUI.getCountriesNames().contains("Aruba"))) {
			value = true;
		}
		assertTrue(value);
	}

	// COUNTRY IS NOT IN THE COUNTRY DROP DOWN MENU
	@Test
	public void CountryTest9() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getCountriesNames().contains("Canada"))) {
			value = true;
		}
		assertTrue(value);
	}

	// COUNTRY IS NOT IN THE COUNTRY DROP DOWN MENU
	@Test
	public void CountryTest10() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getCountriesNames().contains("USA"))) {
			value = true;
		}
		assertTrue(value);
	}

/////////////////////////// USE CASE 3 TESTING ////////////////////////////////////////////

	// ANALYSIS IS NOT IN THE ANALYSIS DROP DOWN MENU
	@Test
	public void AnalysisTest11() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getMethodsNames().contains("Number of bottles per capita"))) {
			value = true;
		}
		assertTrue(value);
	}

	// ANALYSIS IS IN THE ANALYSIS DROP DOWN MENU
	@Test
	public void AnalysisTest12() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if ((mainGUI.getMethodsNames()
				.contains("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution"))) {
			value = true;
		}
		assertTrue(value);
	}

	// ANALYSIS IS NOT SELECTED IN THE ANALYSIS DROP DOWN MENU
	@Test
	public void AnalysisTest13() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getMethodsNames().contains(""))) {
			value = true;
		}
		assertTrue(value);
	}

	// ANALYSIS IS IN THE ANALYSIS DROP DOWN MENU
	@Test
	public void AnalysisTest14() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if ((mainGUI.getMethodsNames().contains("Annual percentage change of PM2.5 air pollution & Forest area"))) {
			value = true;
		}
		assertTrue(value);
	}

	// ANALYSIS IS NOT IN THE ANALYSIS DROP DOWN MENU
	@Test
	public void AnalysisTest15() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		boolean value = false;
		if (!(mainGUI.getMethodsNames().contains("Waste percentage per capita"))) {
			value = true;
		}
		assertTrue(value);
	}
/////////////////////////// USE CASE 4 TESTING ////////////////////////////////////////////

	// THE START DATE IS MORE THAN THE END DATE, ERROR POP UP
	@Test
	public void YearTest16() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		mainGUI.createGraph("can", 2021, 2020, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// THE START DATE IS LESS THAN THE END DATE, NO ERROR POP UP
	@Test
	public void YearTest17() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		mainGUI.createGraph("can", 2010, 2020, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// THE START DATE IS EQUAL TO THE END DATE, ERROR POP UP
	@Test
	public void YearTest18() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		mainGUI.createGraph("can", 2021, 2020, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// THE START DATE IS NOT SELECTED BUT THE END DATE IS, EXCEPTION THROWN
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void YearTest19() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		exception.expect(IndexOutOfBoundsException.class);
		mainGUI.createGraph("can", 0, 2021, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// THE START DATE IS SLECTED BUT THE END DATE ISN'T, ERROR POP UP
	@Test
	public void YearTest20() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		mainGUI.createGraph("can", 2021, 0, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

/////////////////////////// USE CASE 5 TESTING ////////////////////////////////////////////

	// ADDING A TIME SERIES CHART TO A TIME SERIES ANALYSIS, NO ERROR POP UP
	@Test
	public void AddGraphTest21() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Line Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.createGraph("can", 2011, 2021, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// ADDING A CATEGORY DATA CHART TO A TIME SERIES ANALYSIS, ERROR POPS UP
	@Test
	public void AddGraphTest22() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Pie Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.createGraph("can", 2011, 2021, "Annual percentage change of PM2.5 air pollution & Forest area");
	}

	// ADDING A CATEGORY DATA CHART TO A CATEGORY DATA ANALYSIS, NO ERROR POPS UP
	@Test
	public void AddGraphTest23() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Pie Chart", "Average Forest area (as % of land area)");
		this.mainGUI.createGraph("can", 2011, 2021, "Average Forest area (as % of land area)");
	}

	// ADDING A TIME SERIES DATA CHART TO A CATEGORY DATA ANALYSIS, ERROR POPS UP
	@Test
	public void AddGraphTest24() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Line Chart", "Average Forest area (as % of land area)");
		this.mainGUI.createGraph("can", 2011, 2021, "Average Forest area (as % of land area)");
	}

	// NO CHART ADDED TO A CATEGORY DATA ANALYSIS, ERROR POPS UP
	@Test
	public void AddGraphTest25() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "", "Average Forest area (as % of land area)");
		this.mainGUI.createGraph("can", 2011, 2021, "Average Forest area (as % of land area)");
	}

/////////////////////////// USE CASE 6 TESTING ////////////////////////////////////////////

	// REMOVING BAR CHART FROM ANALYSIS
	@Test
	public void RemoveGraphTest26() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		// ADD THE CHART
		observer.notifySubs(true, "Bar Chart", "Average Forest area (as % of land area)");
		// REMOVE CHART
		observer.notifySubs(false, "Bar Chart", "Average Forest area (as % of land area)");
		ArrayList<AnalysisInterface> observerView = observer.getViewList();
		boolean value = true;
		// STORE THE ANALYSIS VARIABLE LOCALLY TO BE MANIPULATED
		AnalysisInterface analysis = null;
		for (int i = 0; i < observerView.size(); i++) {
			if (observerView.get(i).getName().equals("Average Forest area (as % of land area)")) {
				analysis = observerView.get(i);
			}
		}
		// FIND THE CHART IN THE ANALYSIS' CHART LIST AND CHECK FOR THE CHART STATUS
		for (String keys : analysis.getChartSystemView().keySet()) {
			if (keys.equals("Bar Chart")) {
				value = analysis.getChartSystemView().get(keys);
				break;
			}
		}
		assertFalse(value);
	}

	// REMOVING PIE CHART FROM ANALYSIS
	@Test
	public void RemoveGraphTest27() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		// ADD THE CHART
		observer.notifySubs(true, "Pie Chart", "Average Forest area (as % of land area)");
		// REMOVE CHART
		observer.notifySubs(false, "Pie Chart", "Average Forest area (as % of land area)");
		ArrayList<AnalysisInterface> observerView = observer.getViewList();
		boolean value = true;
		// STORE THE ANALYSIS VARIABLE LOCALLY TO BE MANIPULATED
		AnalysisInterface analysis = null;
		for (int i = 0; i < observerView.size(); i++) {
			if (observerView.get(i).getName().equals("Average Forest area (as % of land area)")) {
				analysis = observerView.get(i);
			}
		}
		// FIND THE CHART IN THE ANALYSIS' CHART LIST AND CHECK FOR THE CHART STATUS
		for (String keys : analysis.getChartSystemView().keySet()) {
			if (keys.equals("Pie Chart")) {
				value = analysis.getChartSystemView().get(keys);
				break;
			}
		}
		assertFalse(value);
	}

	// REMOVING REPORT FROM ANALYSIS
	@Test
	public void RemoveGraphTest28() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		// ADD THE CHART
		observer.notifySubs(true, "Report", "Average Forest area (as % of land area)");
		// REMOVE CHART
		observer.notifySubs(false, "Report", "Average Forest area (as % of land area)");
		ArrayList<AnalysisInterface> observerView = observer.getViewList();
		boolean value = true;
		// STORE THE ANALYSIS VARIABLE LOCALLY TO BE MANIPULATED
		AnalysisInterface analysis = null;
		for (int i = 0; i < observerView.size(); i++) {
			if (observerView.get(i).getName().equals("Average Forest area (as % of land area)")) {
				analysis = observerView.get(i);
			}
		}
		// FIND THE CHART IN THE ANALYSIS' CHART LIST AND CHECK FOR THE CHART STATUS
		for (String keys : analysis.getChartSystemView().keySet()) {
			if (keys.equals("Report")) {
				value = analysis.getChartSystemView().get(keys);
				break;
			}
		}
		assertFalse(value);
	}

	// REMOVING LINE CHART FROM ANALYSIS
	@Test
	public void RemoveGraphTest29() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		// ADD THE CHART
		observer.notifySubs(true, "Line Chart", "Ratio of CO2 emissions and GDP per capita");
		// REMOVE CHART
		observer.notifySubs(false, "Line Chart", "Ratio of CO2 emissions and GDP per capita");
		ArrayList<AnalysisInterface> observerView = observer.getViewList();
		boolean value = true;
		// STORE THE ANALYSIS VARIABLE LOCALLY TO BE MANIPULATED
		AnalysisInterface analysis = null;
		for (int i = 0; i < observerView.size(); i++) {
			if (observerView.get(i).getName().equals("Ratio of CO2 emissions and GDP per capita")) {
				analysis = observerView.get(i);
			}
		}
		// FIND THE CHART IN THE ANALYSIS' CHART LIST AND CHECK FOR THE CHART STATUS
		for (String keys : analysis.getChartSystemView().keySet()) {
			if (keys.equals("Line Chart")) {
				value = analysis.getChartSystemView().get(keys);
				break;
			}
		}
		assertFalse(value);
	}

	// REMOVING SCATTER CHART FROM ANALYSIS
	@Test
	public void RemoveGraphTest30() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		// ADD THE CHART
		observer.notifySubs(true, "Scatter Chart", "Ratio of CO2 emissions and GDP per capita");
		// REMOVE CHART
		observer.notifySubs(false, "Scatter Chart", "Ratio of CO2 emissions and GDP per capita");
		ArrayList<AnalysisInterface> observerView = observer.getViewList();
		boolean value = true;
		// STORE THE ANALYSIS VARIABLE LOCALLY TO BE MANIPULATED
		AnalysisInterface analysis = null;
		for (int i = 0; i < observerView.size(); i++) {
			if (observerView.get(i).getName().equals("Ratio of CO2 emissions and GDP per capita")) {
				analysis = observerView.get(i);
			}
		}
		// FIND THE CHART IN THE ANALYSIS' CHART LIST AND CHECK FOR THE CHART STATUS
		for (String keys : analysis.getChartSystemView().keySet()) {
			if (keys.equals("Scatter Chart")) {
				value = analysis.getChartSystemView().get(keys);
				break;
			}
		}
		assertFalse(value);
	}

/////////////////////////// USE CASE 7 TESTING ////////////////////////////////////////////

	// FETCHING DATA FOR VALID COUNTRY, START AND END YEAR, CHART TYPE AND ANALYSIS
	@Test
	public void fetchDataTest31() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		boolean value = false;
		// LINE CHART CHOSEN
		if ((mainGUI.getCountriesNames().contains("Andorra"))) {
			observer.notifySubs(true, "Line Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
			this.mainGUI.createGraph("AND", 2011, 2021,
					"Annual percentage change of PM2.5 air pollution & Forest area");
			value = true;
		}
		assertTrue(value);
	}

	// FETCHING DATA FOR INVALID COUNTRY, START AND END YEAR, CHART TYPE AND
	// ANALYSIS
	@Test
	public void fetchDataTest32() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		boolean value = false;
		// LINE CHART CHOSEN
		if ((mainGUI.getCountriesNames().contains("Canada"))) {
			observer.notifySubs(true, "Line Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
			this.mainGUI.createGraph("can", 2011, 2021,
					"Annual percentage change of PM2.5 air pollution & Forest area");
			value = true;
		}
		assertFalse(value);
	}

	// FETCHING DATA FOR VALID COUNTRY, START AND END YEAR, INVALID CHART TYPE AND
	// ANALYSIS ===> ERROR POPS UP
	@Test
	public void fetchDataTest33() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Pie Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.createGraph("AND", 2011, 2021, "Average Forest area (as % of land area)");
	}

	// FETCHING DATA FOR VALID COUNTRY, START AND END YEAR, CHART TYPE AND ANALYSIS
	@Test
	public void fetchDataTest34() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		boolean value = false;
		// LINE CHART CHOSEN
		if ((mainGUI.getCountriesNames().contains("Andorra"))) {
			observer.notifySubs(true, "Line Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
			this.mainGUI.createGraph("AND", 2011, 2021,
					"Annual percentage change of PM2.5 air pollution & Forest area");
			value = true;
		}
		assertTrue(value);
	}

	// FETCHING DATA FOR VALID COUNTRY, INVALID START AND END YEAR, VALID CHART TYPE
	// AND ANALYSIS ===> ERROR POPS UP
	@Test
	public void fetchDataTest35() throws IOException, ParseException {
		this.mainGUI = mainGUI.getInstance();
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Pie Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.createGraph("AND", 2011, 2001, "Average Forest area (as % of land area)");
	}
/////////////////////////// USE CASE 8 TESTING ////////////////////////////////////////////

	// RENDER THE BAR GRAPH
	@Test
	public void RenderTest36() throws IOException, ParseException, InterruptedException {
		this.mainGUI = mainGUI.getInstance();
		this.mainGUI.Start(true);
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Bar Chart", "Average Forest area (as % of land area)");
		this.mainGUI.setViewObserver(observer);
		this.mainGUI.createGraph("AND", 2000, 2018, "Average Forest area (as % of land area)");
		this.mainGUI.packFrame();
		Thread.sleep(1000);
	}

	// RENDER THE PIE GRAPH
	@Test
	public void RenderTest37() throws IOException, ParseException, InterruptedException {
		this.mainGUI = mainGUI.getInstance();
		this.mainGUI.Start(true);
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Pie Chart", "Average Forest area (as % of land area)");
		this.mainGUI.setViewObserver(observer);
		this.mainGUI.createGraph("AND", 2000, 2018, "Average Forest area (as % of land area)");
		this.mainGUI.packFrame();
		Thread.sleep(1000);
	}

	// RENDER THE REPORT
	@Test
	public void RenderTest38() throws IOException, ParseException, InterruptedException {
		this.mainGUI = mainGUI.getInstance();
		this.mainGUI.Start(true);
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Report", "Average Forest area (as % of land area)");
		this.mainGUI.setViewObserver(observer);
		this.mainGUI.createGraph("AND", 2000, 2018, "Average Forest area (as % of land area)");
		this.mainGUI.packFrame();
		Thread.sleep(1000);
	}

	// RENDER THE LINE CHART
	@Test
	public void RenderTest39() throws IOException, ParseException, InterruptedException {
		this.mainGUI = mainGUI.getInstance();
		this.mainGUI.Start(true);
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Line Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.setViewObserver(observer);
		this.mainGUI.createGraph("AND", 2000, 2018, "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.packFrame();
		Thread.sleep(1000);
	}

	// RENDER THE SCATTER CHART
	@Test
	public void RenderTest40() throws IOException, ParseException, InterruptedException {
		this.mainGUI = mainGUI.getInstance();
		this.mainGUI.Start(true);
		ObserverInterface observer = this.mainGUI.getViewObserver();
		observer.notifySubs(true, "Scatter Chart", "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.setViewObserver(observer);
		this.mainGUI.createGraph("AND", 2000, 2018, "Annual percentage change of PM2.5 air pollution & Forest area");
		this.mainGUI.packFrame();
		Thread.sleep(1000);
	}
}