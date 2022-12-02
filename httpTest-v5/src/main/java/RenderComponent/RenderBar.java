package RenderComponent;
/**
 * This is the render class for the bar graph. This is being implemented with the RenderInterface class.   
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

public class RenderBar implements RenderInterface {

	Object dataset; 
	private JPanel west;
	String analysisType;
	
	/**
	 * Set the bar graph with the data and type of analysis.
	 * @param dataset
	 * @param west
	 * @param analysisType
	 */
	public RenderBar(Object dataset, JPanel west, String analysisType) {
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	/**
	 * This method adds the selected data and type of analysis to a new bar graph.
	 */
	public void visualize() {
		CategoryDataset dataset2 = (CategoryDataset) this.dataset;
		JFreeChart chart = ChartFactory.createBarChart(this.analysisType, "Data Types", "Values", dataset2);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.west.add(chartPanel);
	}
}
