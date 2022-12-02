package RenderComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * This is the render class for the scatter chart. This is being implemented with the RenderInterface class.   
 * 
 * @author Kenny Tan (216532152)
 * @author Nicholas Landivar (216363640)
 * @author Maulik Suryavanshi (217184615)
 * @author Dinuja Wattage (217564204)
 */
public class RenderScatter implements RenderInterface {

	Object dataset; 
	private JPanel west;
	String analysisType;
	
	/**
	 * Set the scatter chart with the data and type of analysis.
	 * @param dataset
	 * @param west
	 * @param analysisType
	 */
	public RenderScatter(Object dataset, JPanel west, String analysisType) {
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	/**
	 * This method adds the selected data and type of analysis to a scatter chart.
	 */
	public void visualize() {
		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
		plot.setDataset(0, (XYDataset) this.dataset);
		plot.setRenderer(0, itemrenderer1);
		
		DateAxis domainAxis = new DateAxis("Year");
		
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));
		plot.mapDatasetToRangeAxis(0, 0);

		JFreeChart scatterChart = new JFreeChart(this.analysisType, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.west.add(chartPanel);
	}
}
