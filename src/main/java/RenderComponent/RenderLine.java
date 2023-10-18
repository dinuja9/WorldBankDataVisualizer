package RenderComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * This is the render class for the line graph. This is being implemented with the RenderInterface class.   
 */
public class RenderLine implements RenderInterface{
	Object dataset; 
	private JPanel west;
	String analysisType;
	
	/**
	 * Set the line graph with the data and type of analysis.
	 * @param dataset
	 * @param west
	 * @param analysisType
	 */
	public RenderLine(Object dataset, JPanel west, String analysisType) {
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	/**
	 * This method adds the selected data and type of analysis to a new line graph.
	 */
	public void visualize() {
		// RENDER THE CHART
		JFreeChart chart = ChartFactory.createXYLineChart(this.analysisType, "Year", "", (XYDataset) this.dataset, PlotOrientation.VERTICAL,
				true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis("Percentage change (%)"));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.west.add(chartPanel);
	}
}
