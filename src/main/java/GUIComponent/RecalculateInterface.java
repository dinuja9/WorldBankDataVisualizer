package GUIComponent;

import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * This is the Recalculate interface and it is responsible for the functions whenever the user decides to hit the recalculate button.  
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */

public interface RecalculateInterface {
	public JButton getAddView();
	public JButton getRemoveView();
	public String getCountryCode();
	public void setCountryCode();
	public int getStartDate();
	public void setStartDate();
	public int getEndDate();
	public void setEndDate();
	public String getAnalysis();
	public void setAnalysis(String analysis);
	public JButton getRecalculate();
	public String getChart();
	public void setChart();
	public JComboBox<String> getCountriesList();
	public JComboBox<String> getFromList();
	public JComboBox<String> getToList();
	public JComboBox<String> getViewsList();
	public JComboBox<String> getMethodsList(); 

}
