package statsVisualiser.gui;

import java.util.ArrayList;

public interface ApiCaller {
	public void callAPI();
	public ArrayList<Integer> getYear();
	public ArrayList<Double> getValueOfYear();
}
