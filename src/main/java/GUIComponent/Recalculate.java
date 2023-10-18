package GUIComponent;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * This is the Recalculate class which will be used as a global reference of
 * user inputs.
 */

public class Recalculate implements RecalculateInterface {
	public String countryCode;
	public int startDate;
	public int endDate;
	public String analysis;
	public String chart;

	public JComboBox<String> countriesList;
	public JComboBox<String> fromList;
	public JComboBox<String> toList;
	public JComboBox<String> viewsList;
	public JComboBox<String> methodsList;
	public JButton recalculate;
	public JButton addView;
	public JButton removeView;
	public HashMap<String, String> worldBankCountries;

	/**
	 * The constructor for the recalculate button
	 * 
	 * @param worldBankCountries
	 * @param recalculate
	 * @param countriesList
	 * @param fromList
	 * @param toList
	 * @param viewsList
	 * @param methodsList
	 * @param addView
	 * @param removeView
	 */
	public Recalculate(HashMap<String, String> worldBankCountries, JButton recalculate, JComboBox<String> countriesList,
			JComboBox<String> fromList, JComboBox<String> toList, JComboBox<String> viewsList,
			JComboBox<String> methodsList, JButton addView, JButton removeView) {
		this.countryCode = "";
		this.analysis = "";
		this.endDate = 0;
		this.startDate = 0;
		this.chart = "";
		this.worldBankCountries = worldBankCountries;
		this.countriesList = countriesList;
		this.fromList = fromList;
		this.toList = toList;
		this.viewsList = viewsList;
		this.methodsList = methodsList;
		this.recalculate = recalculate;
		this.addView = addView;
		this.removeView = removeView;
	}

	/**
	 * @return the add view button
	 */
	public JButton getAddView() {
		return addView;
	}

	/**
	 * @return the remove view button
	 */
	public JButton getRemoveView() {
		return removeView;
	}

	/**
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * sets the country code to what the user selected
	 */
	public void setCountryCode() {
		this.countryCode = this.worldBankCountries.get((String) countriesList.getSelectedItem());
	}

	/**
	 * @return the start date
	 */
	public int getStartDate() {
		return startDate;
	}

	/**
	 * sets the start date to what the user selected
	 */
	public void setStartDate() {
		this.startDate = Integer.parseInt((String) fromList.getSelectedItem());
	}

	/**
	 * @return the end date
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * sets the end date to what the user selected
	 */
	public void setEndDate() {
		this.endDate = Integer.parseInt((String) toList.getSelectedItem());
	}

	/**
	 * @return the analysis
	 */
	public String getAnalysis() {
		return analysis;
	}

	/**
	 * sets the analysis to what the user selected
	 */
	public void setAnalysis(String analysis) {
		this.analysis = (String) methodsList.getSelectedItem();
	}

	/**
	 * @return the recalculate button
	 */
	public JButton getRecalculate() {
		return recalculate;
	}

	/**
	 * @return the chart type
	 */
	public String getChart() {
		return chart;
	}

	/**
	 * sets the chart type to what the user selected
	 */
	public void setChart() {
		this.chart = (String) viewsList.getSelectedItem();
	}

	/**
	 * @return the country list drop down on the GUI
	 */
	public JComboBox<String> getCountriesList() {
		return countriesList;
	}

	/**
	 * @return the start date list drop down on the GUI
	 */
	public JComboBox<String> getFromList() {
		return fromList;
	}

	/**
	 * @return the end date list drop down on the GUI
	 */
	public JComboBox<String> getToList() {
		return toList;
	}

	/**
	 * @return the chart list drop down on the GUI
	 */
	public JComboBox<String> getViewsList() {
		return viewsList;
	}

	/**
	 * @return the analysis list drop down on the GUI
	 */
	public JComboBox<String> getMethodsList() {
		return methodsList;
	}

}
