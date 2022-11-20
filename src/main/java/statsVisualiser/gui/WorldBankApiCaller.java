package statsVisualiser.gui;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * The main functionality of this class is to call the WorldBankApi and store 
 * the data into arrays
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class WorldBankApiCaller implements ApiCaller {
	public ArrayList<Double> valueOfYear = new ArrayList<Double>();
	public ArrayList<Integer> year = new ArrayList<Integer>();
	public String urlString = "";

	/**
	 * Initializes the WorldBankApiCaller with the URL string for data
	 * fetching, as well as initializes the arrays for years and values
	 * of those years.
	 * @param valueOfYear
	 * @param year
	 * @param urlString
	 */
	public WorldBankApiCaller(ArrayList<Double> valueOfYear,ArrayList<Integer> year, String urlString) {
		this.valueOfYear = valueOfYear;
		this.year = year;
		this.urlString = urlString;
	}
	
	/**
	 * Makes calls to WorldBankApi using the paramaters that were initialized
	 * in the constructor. The data is then stored back into the arrays for
	 * years and values of years.
	 */
	public void callAPI(){
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
					// GET FOR EACH ENTRY THE YEAR FROM THE �date� FIELD
					year.add((Integer) jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt());
					// CHECK IF THERE IS A VALUE FOR THE POPULATION FOR A
					// GIVEN YEAR
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
						valueOfYear.add(0.0);
					}
					else {
						// GET THE POPULATION FOR THE GIVEN YEAR FROM THE
						// �value� FIELD
						valueOfYear.add((double) jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsInt());
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	public ArrayList<Integer> getYear() {
		return this.year;
	}
	public ArrayList<Double> getValueOfYear(){
		return this.valueOfYear;
	}
}
