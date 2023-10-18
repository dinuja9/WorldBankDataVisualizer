package GUIComponent;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

/**
 * The main functionality of this class is to validate the login credentials.
 */
public class LoginChecker{
	private String user;
	private String pass;
	private JSONArray allLogins = new JSONArray();
	private JSONObject login = new JSONObject();
	private Object holder = null;
	private JSONParser parse = new JSONParser();
	
	/**
	 * Initializes the checker for login credentials. Reads the initial
	 * login.JSON file that stores all the login credentials within the
	 * system. All the login credentials are then parsed, and moved into
	 * an array.
	 * @param user's username
	 * @param user's password
	 * @throws IOException if file does not exist/is corrupted
	 * @throws ParseException if there is an error while parsing the file name
	 */
	public LoginChecker(String user, String pass) throws IOException, Exception {
		this.user = user;
		this.pass = pass;
		try {
			FileReader lFile =  new FileReader("login.JSON");
			holder = parse.parse(lFile);
			allLogins = (JSONArray) holder;
			lFile.close();
		} catch (FileNotFoundException e) {	
			System.out.println("Error");
		}
	}
	
	/**
	 * Validates if the login credentials are correct or not.
	 * @return true if login is valid, false otherwise
	 */
	public boolean checker() {
		boolean value = false;
		int size = allLogins.size();
		
		login.put("Username", this.user);
		login.put("Password", this.pass);
		
		for(int i = 0; i< size; i++) {
			if(login.equals(allLogins.get(i))) {
				value = true;
				break;
			}
			else {
				value = false;
			}
		}
		return value;
	}
}
