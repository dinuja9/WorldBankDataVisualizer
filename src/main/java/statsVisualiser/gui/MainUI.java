package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
/**
 * The main functionality of this class is to draw main application
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class MainUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static MainUI instance;
	Recalculate recButton;
	private JPanel west;
	
	public static JFrame frame;
	/*************************************************
	 * FALL 2022
	 * EECS 3311 GUI SAMPLE CODE
	 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
	 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	private MainUI() throws IOException, ParseException  {
		// Set window title
		super("Country Statistics");

		int startDate = 0;
		int endDate = 0;
		String analysis = "";
		String chart = "";
		String country = "";
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		
		// OPEN CLOSE PRINCIPLE FOR COUNTRIES IS SATIFIED
		JSONArray allCountries = new JSONArray();
		JSONParser parse = new JSONParser();
		
		FileReader countryFile =  new FileReader("countries.JSON");
		allCountries = (JSONArray) parse.parse(countryFile);
		countryFile.close();
		
		for(int i = 0; i< allCountries.size(); i++) {
			JSONObject obj1 = new JSONObject((Map) allCountries.get(i));
			countriesNames.add((String) obj1.get("Country"));	
		}
		countriesNames.sort(null);
		///////////////////////////////////////////////////////
		
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		years.add("Select");
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Select");
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");
		
		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Select");
		methodsNames.add("Annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution");
		methodsNames.add("Annual percentage change of PM2.5 air pollution & Forest area");
		methodsNames.add("Ratio of CO2 emissions and GDP per capita");
		methodsNames.add("Average Forest area (as % of land area)");
		methodsNames.add("Government Expenditure: Education vs Other");
		methodsNames.add("Health Expenditure vs Hospital Beds/1000 People");
		methodsNames.add("Problems in accessing health care vs Infant mortality rate (per 1,000 live births)");
		methodsNames.add("Annual percentage change Government expenditure on education and current health expenditure");
		
		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		JPanel east = new JPanel();

		// Set charts region
		west = new JPanel();
		
		west.setLayout(new GridLayout(2, 0));
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		
		recButton = new Recalculate(country, startDate, endDate, analysis, recalculate, chart, countriesList,
				fromList, toList, viewsList, methodsList, addView, removeView);
		// ACTION LISTENERS
		recButton.getCountriesList().addActionListener(this);
		recButton.getFromList().addActionListener(this);
		recButton.getToList().addActionListener(this);
		recButton.getViewsList().addActionListener(this);
		recButton.getMethodsList().addActionListener(this);
		recButton.getRecalculate().addActionListener(this);
		recButton.getAddView().addActionListener(this);
		recButton.getRemoveView().addActionListener(this);
		
	}		
		
	/**
	 * Static factory method for MainUI.
	 * @return instance of MainUI
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static MainUI getInstance() throws IOException, ParseException {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	/**
	 * Draws time series chart to frame.
	 * @param west is the destination panel
	 * @param chart 
	 * @param analysis 
	 * @param endDate 
	 * @param startDate 
	 * @param country 
	 */
	private void createTimeSeries(String country, int startDate, int endDate, String analysisType, String chartType) {
		SeriesGraph graph = new SeriesGraph(analysisType, chartType);
		AnalysisFactory factory = new AnalysisFactory();
		AnalysisInterface a1 = factory.createAnalysis(analysisType);
		graph.setAnalysis(a1);
		graph.executeStrategy(west, country, startDate, endDate, chartType);
	}
	/**
	 * Gets instance of MainUI in order to start application. 
	 * @param b determines if application is able to start or not
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void Start(boolean b) throws IOException, ParseException {
		frame = MainUI.getInstance();
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(e.getSource()==recButton.getRecalculate()) {
			createTimeSeries(recButton.getCountry(),recButton.getStartDate(),recButton.getEndDate(), recButton.getAnalysis(), recButton.getChart());
			frame.pack();
		}
		else if (e.getSource()==recButton.getCountriesList()) {
			recButton.setCountry();
			System.out.println(recButton.getCountry());
		}
		else if (e.getSource()==recButton.getFromList()) {
			recButton.setStartDate();
			System.out.println(recButton.getStartDate());
		}
		else if (e.getSource()==recButton.getToList()) {
			recButton.setEndDate();
			System.out.println(recButton.getEndDate());
		}
		else if (e.getSource()==recButton.getViewsList()) {
			recButton.setChart();
			System.out.println(recButton.getChart());
		}
		// ADD VIEW BUTTON
		else if (e.getSource()==recButton.getAddView()) {
			 
			System.out.println();
		}
		// REMOVE VIEW BUTTON
		else if (e.getSource()==recButton.getRemoveView()) {
			
		}
		else {
			recButton.setAnalysis(recButton.getAnalysis());
			System.out.println(recButton.getAnalysis());
		}
	}

}