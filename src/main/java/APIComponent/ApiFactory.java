package APIComponent;

/**
 * This class works with our factory method to return a new WorldBankApiCaller
 * object so we can get the data.
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */

public class ApiFactory {
	public ApiCaller api;
	/**
	 * A method that returns a new WorldBankApiCaller object with the specified parameters
	 * so we can use the data.
	 * @param startDate
	 * @param endDate
	 * @param country
	 * @param analysis
	 * @return a new WorldBankApiCaller object
	 */
	public ApiCaller createAPI(int startDate, int endDate, String country, String analysis) {
		api = new WorldBankApiCaller(startDate, endDate, country, analysis);
		return api;
	}

}
