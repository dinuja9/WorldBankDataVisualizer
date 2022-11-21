package statsVisualiser.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Recalculate {
	String country ="";
	int startDate = 0;
	int endDate = 0; 
	String analysis = "";
	JPanel west;
	String chart = "";
	
	JComboBox<String> countriesList;
	JComboBox<String> fromList;
	JComboBox<String> toList;
	JComboBox<String> viewsList;
	JComboBox<String> methodsList;
	JButton recalculate;

	public Recalculate(String country, int startDate, int endDate, String analysis, JButton recalculate, String chart, JComboBox<String> countriesList,
			JComboBox<String> fromList, JComboBox<String> toList, 
			JComboBox<String> viewsList, JComboBox<String> methodsList) {
		this.country = country;
		this.analysis = analysis;
		this.endDate = endDate;
		this.startDate = startDate;
		this.chart = chart;
		
		this.countriesList = countriesList;
		this.fromList = fromList;
		this.toList = toList;
		this.viewsList = viewsList;
		this.methodsList = methodsList; 
		this.recalculate = recalculate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry() {
		this.country =  ((String)countriesList.getSelectedItem()).substring(0, 3).toLowerCase();
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate() {
		try {
			this.startDate = Integer.parseInt((String)fromList.getSelectedItem());
		}catch(Exception e) {
			System.out.println("Select a year");
		}
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate() {
		try {	
			this.endDate = Integer.parseInt((String) toList.getSelectedItem());
		}catch(Exception e) {
			System.out.println("Select a year");
		}
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = (String) methodsList.getSelectedItem();
	}

	public JButton getRecalculate() {
		return recalculate;
	}
	
	public String getChart() {
		return chart;
	}

	public void setChart() {
		this.chart = (String) viewsList.getSelectedItem();
	}
	// we don't need to set anything for the lists because the user does that
	// in real time
	public JComboBox<String> getCountriesList() {
		return countriesList;
	}

	public JComboBox<String> getFromList() {
		return fromList;
	}

	public JComboBox<String> getToList() {
		return toList;
	}

	public JComboBox<String> getViewsList() {
		return viewsList;
	}

	public JComboBox<String> getMethodsList() {
		return methodsList;
	}
	
}
