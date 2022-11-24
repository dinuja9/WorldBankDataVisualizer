package statsVisualiser.gui;

import java.util.ArrayList;

public class ApiFactory {

	public ApiCaller createAPI(ArrayList<Double> valueOfYear,ArrayList<Integer> year, int startDate, int endDate, String country, String analysis) {
		// TODO Auto-generated method stub
		return new WorldBankApiCaller(valueOfYear, year, startDate, endDate, country, analysis);
	}

}
