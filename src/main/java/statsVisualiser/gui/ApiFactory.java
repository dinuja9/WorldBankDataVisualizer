package statsVisualiser.gui;

import java.util.ArrayList;

public class ApiFactory {

	public ApiCaller createAPI(ArrayList<Double> valueOfYear,ArrayList<Integer> year, String urlString) {
		// TODO Auto-generated method stub
		return new WorldBankApiCaller(valueOfYear, year, urlString);
	}

}
