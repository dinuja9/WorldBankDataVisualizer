package APIComponent;

import java.util.ArrayList;
/**
 * The class is the interface for the api methods that we need
 * to get the information for the analysis.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public interface ApiCaller {
	public void callAPI();
	public ArrayList<Integer> getYear();
	public ArrayList<Double> getValueOfYear();
}
