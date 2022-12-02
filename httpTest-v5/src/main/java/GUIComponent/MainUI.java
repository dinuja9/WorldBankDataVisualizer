package GUIComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import AnalysisComponent.AnalysisFactory;
import AnalysisComponent.AnalysisGraph;
import AnalysisComponent.AnalysisInterface;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
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
	public RecalculateInterface recButton;
	private JPanel west;
	boolean systemView = false;
	public static JFrame frame;
	public ObserverInterface observer;

	/*************************************************
	 * FALL 2022 EECS 3311 GUI SAMPLE CODE ONLT AS A REFERENCE TO SEE THE USE OF THE
	 * jFree FRAMEWORK THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	private MainUI() throws IOException, ParseException {
		// Set window title
		super("Country Statistics");

		// Set top bar
//////////////////// ADD ALL COUNTRIES IN WORLD BANK API //////////////////////////////////////
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		HashMap<String, String> worldBankCountries = new HashMap<String, String>();
		
		try {
			File fXmlFile = new File("worldbank.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("wb:country");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					worldBankCountries.put(eElement.getElementsByTagName("wb:name").item(0).getTextContent(),
							eElement.getAttribute("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// removing unwanted countries form the countries listed in the JSON file
		JSONArray allCountries = new JSONArray();
		JSONParser parse = new JSONParser();

		FileReader countryFile = new FileReader("countries.JSON");
		allCountries = (JSONArray) parse.parse(countryFile);
		countryFile.close();

		for (int i = 0; i < allCountries.size(); i++) {
			JSONObject obj1 = new JSONObject((Map) allCountries.get(i));
			if (worldBankCountries.containsKey((String) obj1.get("Country"))) {
				worldBankCountries.remove(obj1.get("Country"));
			}
		}
		for (String key : worldBankCountries.keySet()) {
			countriesNames.add(key);
		}

		countriesNames.sort(null);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		countriesNames.add(0, "Select");
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
		// OPEN CLOSE PRINCIPLE FOR ANALYSIS IS SATIFIED
		JSONArray allAnalysis = new JSONArray();
		JSONParser parseAnalysis = new JSONParser();
		FileReader analysisFile = new FileReader("analysisNames.JSON");

		allAnalysis = (JSONArray) parseAnalysis.parse(analysisFile);
		analysisFile.close();
		for (int i = 0; i < allAnalysis.size(); i++) {
			JSONObject obj = new JSONObject((Map) allAnalysis.get(i));
			methodsNames.add((String) obj.get("type"));
		}
		methodsNames.sort(null);
		methodsNames.add(0, "Select");
		///////////////////////////////////////////////////////

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

		recButton = new Recalculate(worldBankCountries, recalculate, countriesList, fromList, toList, viewsList,
				methodsList, addView, removeView);
		// ACTION LISTENERS
		recButton.getCountriesList().addActionListener(this);
		recButton.getFromList().addActionListener(this);
		recButton.getToList().addActionListener(this);
		recButton.getViewsList().addActionListener(this);
		recButton.getMethodsList().addActionListener(this);
		recButton.getRecalculate().addActionListener(this);
		recButton.getAddView().addActionListener(this);
		recButton.getRemoveView().addActionListener(this);

		/// OBSERVER PATTERN USED HERE
		observer = new ViewObserver();
		for (int i = 1; i < methodsNames.size(); i++) {
			AnalysisFactory factory = new AnalysisFactory();
			AnalysisInterface tempAnalysis = factory.createAnalysis(methodsNames.get(i));
			observer.subscribe(tempAnalysis);
		}
	}

	/**
	 * Static factory method for MainUI.
	 * 
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
	 * 
	 * @param west      is the destination panel
	 * @param chart
	 * @param analysis
	 * @param endDate
	 * @param startDate
	 * @param country
	 */
	private void createTimeSeries(String country, int startDate, int endDate, String analysisType, String chartType,
			boolean systemView) {

		if (startDate >= endDate) {
			JOptionPane.showMessageDialog(null, "Start date must be less than end date!");
		} else {
			AnalysisGraph graph = new AnalysisGraph(analysisType, chartType);
			ArrayList<AnalysisInterface> viewListTemp = observer.getViewList();
			AnalysisInterface slectedAnalysis = null;
			for (int i = 0; i < viewListTemp.size(); i++) {
				if (viewListTemp.get(i).getName().equals(analysisType)) {
					slectedAnalysis = viewListTemp.get(i);
				}
			}
			graph.setAnalysis(slectedAnalysis);
			this.west.removeAll();
			this.west.revalidate();
			this.west.repaint();
			graph.executeStrategy(this.west, country, startDate, endDate, chartType, systemView);
		}
	}

	/**
	 * Gets instance of MainUI in order to start application.
	 * 
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

	// THIS IS THE ACTION LISTER METHOD
	public void actionPerformed(ActionEvent e) {
		// RECALCULATE BUTTON
		if (e.getSource() == recButton.getRecalculate()) {
			if (recButton.getCountryCode() == "" || recButton.getStartDate() == 0 || recButton.getEndDate() == 0
					|| recButton.getAnalysis() == "" || recButton.getChart() == "") {
				JOptionPane.showMessageDialog(null, "Please select everything needed to perform analysis!");
			} else {
				createTimeSeries(recButton.getCountryCode(), recButton.getStartDate(), recButton.getEndDate(),
						recButton.getAnalysis(), recButton.getChart(), systemView);
				frame.pack();
			}
		}
		// COUNTRY DROP DOWN MENU
		else if (e.getSource() == recButton.getCountriesList()) {
			recButton.setCountryCode();
		}
		// START DATE DROP DOWN MENU
		else if (e.getSource() == recButton.getFromList()) {
			if(recButton.getFromList().getSelectedItem().equals(("Select"))){
				JOptionPane.showMessageDialog(null, "Select a start date!");
			}
			else {
				recButton.setStartDate();
				if (recButton.getStartDate() >= recButton.getEndDate() && recButton.getEndDate() > 0
						&& !(recButton.getFromList().getSelectedItem().equals(("Select")))) {
					recButton.setStartDate();
					JOptionPane.showMessageDialog(null, "Start date has to be less than end date!");
				} else {
					recButton.setStartDate();
				}
			}
		}
		// END DATE DROP DOWN MENU
		else if (e.getSource() == recButton.getToList()) {
			if(recButton.getToList().getSelectedItem().equals(("Select"))){
				JOptionPane.showMessageDialog(null, "Select a end date!");
			}
			else {
				recButton.setEndDate();
				if (recButton.getStartDate() >= recButton.getEndDate() && recButton.getStartDate() > 0
						&& !(recButton.getToList().getSelectedItem().equals(("Select")))) {
					recButton.setEndDate();
					JOptionPane.showMessageDialog(null, "End date has to be more than start date!");
				} else {
					recButton.setEndDate();
				}
			}
		}
		// CHART TYPE DROP DOWN
		else if (e.getSource() == recButton.getViewsList()) {
			recButton.setChart();
		}
		// ADD VIEW BUTTON
		else if (e.getSource() == recButton.getAddView()) {
			systemView = true;
			observer.notifySubs(systemView, recButton.getChart(), recButton.getAnalysis());
		}
		// REMOVE VIEW BUTTON
		else if (e.getSource() == recButton.getRemoveView()) {
			systemView = false;
			observer.notifySubs(systemView, recButton.getChart(), recButton.getAnalysis());
		}
		// ANALYSIS DROP DOWN MENU
		else if (e.getSource() == recButton.getMethodsList()) {
			recButton.setAnalysis(recButton.getAnalysis());
		}
	}

}