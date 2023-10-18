package APIComponent;

import java.util.ArrayList;
/**
 * The class is the interface for the api methods that we need
 * to get the information for the analysis.
 * 
 */
public interface ApiCaller {
	public void callAPI();
	public ArrayList<Integer> getYear();
	public ArrayList<Double> getValueOfYear();
}
