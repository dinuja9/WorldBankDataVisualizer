package GUIComponent;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * This is the Recalculate class is responsible for the functions whenever the user decides to hit the recalculate button.  
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class Recalculate {
	String countryCode;
	int startDate;
	int endDate;
	String analysis;
	String chart;

	JComboBox<String> countriesList;
	JComboBox<String> fromList;
	JComboBox<String> toList;
	JComboBox<String> viewsList;
	JComboBox<String> methodsList;
	JButton recalculate;
	JButton addView;
	JButton removeView;
	HashMap<String, String> worldBankCountries;

	/**
	 * Setting the recalculate object with all the specified information. 
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
	 * This method returns the addView button.
	 * @return addView
	 */
	public JButton getAddView() {
		return addView;
	}

	/**
	 * This method returns the removeView button.
	 * @return removeView
	 */
	public JButton getRemoveView() {
		return removeView;
	}

	/**
	 * This method returns the country code.
	 * @return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * This method sets the country code.
	 */
	public void setCountryCode() {
		this.countryCode = this.worldBankCountries.get((String) countriesList.getSelectedItem());
	}

	/**
	 * This method returns the start date.
	 * @return startDate
	 */
	public int getStartDate() {
		return startDate;
	}

	/**
	 * This method sets the start date.
	 */
	public void setStartDate() {
		try {
			this.startDate = Integer.parseInt((String) fromList.getSelectedItem());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select a start date!");
		}

	}

	/**
	 * This method returns the end date.
	 * @return endDate
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * This method sets the end date.
	 */
	public void setEndDate() {
		try {
			this.endDate = Integer.parseInt((String) toList.getSelectedItem());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Select an end date!");
		}

	}

	/**
	 * This method returns the analysis.
	 * @return analysis
	 */
	public String getAnalysis() {
		return analysis;
	}

	/**
	 * This method sets the analysis.
	 */
	public void setAnalysis(String analysis) {
		this.analysis = (String) methodsList.getSelectedItem();
	}

	/**
	 * This method returns the recalculate button.
	 * @return recalculate
	 */
	public JButton getRecalculate() {
		return recalculate;
	}

	/**
	 * This method returns the chart.
	 * @return chart
	 */
	public String getChart() {
		return chart;
	}

	/**
	 * This method sets the chart.
	 */
	public void setChart() {
		this.chart = (String) viewsList.getSelectedItem();
	}

	/**
	 * This method returns the countries list.
	 * @return countriesList
	 */
	public JComboBox<String> getCountriesList() {
		return countriesList;
	}

	/**
	 * This method returns the from list.
	 * @return fromList
	 */
	public JComboBox<String> getFromList() {
		return fromList;
	}

	/**
	 * This method returns the get to list.
	 * @return getToList
	 */
	public JComboBox<String> getToList() {
		return toList;
	}


	/**
	 * This method returns the views list.
	 * @return viewsList
	 */
	public JComboBox<String> getViewsList() {
		return viewsList;
	}

	/**
	 * This method returns the methods list.
	 * @return methodsList
	 */
	public JComboBox<String> getMethodsList() {
		return methodsList;
	}

}
