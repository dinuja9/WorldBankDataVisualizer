package APIComponent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * The main functionality of this class is to call the WorldBankApi and store
 * the data into arrays
 */
public class WorldBankApiCaller implements ApiCaller {
	public ArrayList<Double> valueOfYear;
	public ArrayList<Integer> year;
	public String urlString = "";

	/**
	 * Initializes the WorldBankApiCaller with the URL string for data fetching, as
	 * well as initializes the arrays for years and values of those years.
	 * 
	 * @param valueOfYear
	 * @param year
	 * @param urlString2
	 * @param endDate
	 * @param startDate
	 */
	public WorldBankApiCaller(int startDate, int endDate, String country, String analysis) {
		this.urlString = "http://api.worldbank.org/v2/country/" + country + "/indicator/" + analysis + "?date="
				+ startDate + ":" + endDate + "&format=json";
		this.valueOfYear = new ArrayList<Double>();
		this.year = new ArrayList<Integer>();
	}

	/**
	 * Makes calls to WorldBankApi using the paramaters that were initialized in the
	 * constructor. The data is then stored back into the arrays for years and
	 * values of years.
	 */
	public void callAPI() {
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS - GDP
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				// PROCESS THE JSON AS ONE LINE
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				for (int i = 0; i < sizeOfResults; i++) {
					// GET FOR EACH ENTRY THE YEAR FROM THE DATE FIELD
					year.add((Integer) jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date")
							.getAsInt());
					// CHECK IF THERE IS A VALUE AT GIVEN YEAR
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
						valueOfYear.add(0.0);
					} else {
						// GET THE VALUE AT EACH YEAR AND STORE IN ARRAY
						valueOfYear.add((double) jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsInt());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public ArrayList<Integer> getYear() {
		return this.year;
	}

	public ArrayList<Double> getValueOfYear() {
		return this.valueOfYear;
	}
}
