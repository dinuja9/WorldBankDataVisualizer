package statsVisualiser.gui;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
	
	/*************************************************
	 * FALL 2022
	 * EECS 3311 GUI SAMPLE CODE
	 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
	 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
	 */
	private MainUI() {
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
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
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
		methodsNames.add("annual percentage change of CO2 emissions, Energy use & PM2.5 air pollution");
		methodsNames.add("annual percentage change of PM2.5 air pollution & Forest area");

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
				fromList, toList, viewsList, methodsList);
		// createTimeSeries("can", 2010, 2018, recButton.getAnalysis(), recButton.getChart());

		// ACTION LISTENERS FOR EACH INTERACTIVE BUTTON/DROP DOWN
		//SeriesGraph graph = new SeriesGraph(globalWest, "annual percentage change of PM2.5 air pollution & Forest area", "can", 2001, 2010);

		recButton.getCountriesList().addActionListener(this);
		recButton.getFromList().addActionListener(this);
		recButton.getToList().addActionListener(this);
		recButton.getViewsList().addActionListener(this);
		recButton.getMethodsList().addActionListener(this);
		recButton.getRecalculate().addActionListener(this);
		
		
	}		
		
	/**
	 * Static factory method for MainUI.
	 * @return instance of MainUI
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	/**
	 * Calls methods to draw charts on panel.
	 * @param west is the destination for these methods to draw on
	 */
	private void createCharts(JPanel west) {
	}

	/**
	 * Draws report to frame.
	 * @param west is the destination panel
	 */
	private void createReport(JPanel west) {
		Report report = new Report(west);
	}

	/**
	 * Draws scatter chart to frame.
	 * @param west is the destination panel
	 */
	private void createScatter(JPanel west) {
	}

	/**
	 * Draws pie chart to frame.
	 * @param west is the destination panel
	 */
	private void createPie(JPanel west) {

	}

	/**
	 * Draws bar chart to frame.
	 * @param west is the destination panel
	 */
	private void createBar(JPanel west) {
		
	}

	/**
	 * Draws line chart to frame.
	 * @param west is the destination panel
	 */
	private void createLine(JPanel west) {

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
		SeriesGraph graph = new SeriesGraph(west, analysisType, country, startDate, endDate);
		SeriesGraph graph1 = new SeriesGraph(west, "annual percentage change of PM2.5 air pollution & Forest area", country, startDate, endDate);

	}

	/**
	 * Main method to initialize the login section.
	 * @param args
	 */
	public static void main(String[] args) {
		LoginGUI log = new LoginGUI();
	}

	/**
	 * Gets instance of MainUI in order to start application. 
	 * @param b determines if application is able to start or not
	 */
	public static void Start(boolean b) {
		JFrame frame = MainUI.getInstance();
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(e.getSource()==recButton.getRecalculate()) {
			createTimeSeries(recButton.getCountry(),recButton.getStartDate(),recButton.getEndDate(), recButton.getAnalysis(), recButton.getChart());
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
		else {
			recButton.setAnalysis(recButton.getAnalysis());
			System.out.println(recButton.getAnalysis());
		}
	}

}