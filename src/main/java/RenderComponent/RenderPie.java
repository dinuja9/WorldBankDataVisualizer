package RenderComponent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This is the render class for the pie chart. This is being implemented with the RenderInterface class.   
 */
public class RenderPie implements RenderInterface {

	DefaultCategoryDataset dataset; 
	private JPanel west;
	String analysisType;
	
	/**
	 * Set the pie chart with the data and type of analysis.
	 * @param dataset
	 * @param west
	 * @param analysisType
	 */
	public RenderPie(Object dataset, JPanel west, String analysisType) {
		this.dataset = (DefaultCategoryDataset) dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	/**
	 * This method adds the selected data and type of analysis to a new pie chart.
	 */
	public void visualize() {
		// RENDER THE CHART
		JFreeChart pieChart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", this.dataset,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}
}
