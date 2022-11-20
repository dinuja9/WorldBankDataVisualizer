package statsVisualiser.gui;
import java.util.ArrayList;

/**
 * The main functionality of this class is to fetch the data from our 
 * WorldBankApiCaller class so we can display data.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class GetData {
	public String analysis = ""; //user input
	public int startDate = 0; //user input
	public int endDate = 0; //user input
	public String country = "";
	public String urlString = "";
	public ArrayList<Double> valueOfYear = new ArrayList<Double>();
	public ArrayList<Integer> year = new ArrayList<Integer>();
	public ApiCaller api;
	
	/**
	 * Initializes the data object to store the type of analysis being requested,
	 * the start date of the analysis, the end date of the analysis, and the country
	 * that data is being pulled from.
	 * @param analysis
	 * @param startDate
	 * @param endDate
	 * @param country
	 */
	public GetData(String analysis, int startDate, int endDate, String country) {		
		this.analysis = analysis;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		urlString = "http://api.worldbank.org/v2/country/"+country+"/indicator/"+analysis+"?date="+startDate+":"+endDate+"&format=json";
	}
	
	/**
	 * Makes a call to the WorldBankApiCaller, which then sets the year array and
	 * the valueOfYear array in this class with the data that was fetched.
	 */
	public void fetchData() {
		ApiFactory factory = new ApiFactory();
		ApiCaller api = factory.createAPI(valueOfYear, year,urlString);
		api.callAPI();
		this.year = api.getYear();
		this.valueOfYear = api.getValueOfYear();
	}
}