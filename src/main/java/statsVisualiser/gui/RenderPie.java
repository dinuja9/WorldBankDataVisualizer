package statsVisualiser.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class RenderPie implements RenderInterface {

	Object dataset; 
	private JPanel west;
	String analysisType;
	
	public RenderPie(Object dataset, JPanel west, String analysisType) {
		// TODO Auto-generated constructor stub
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	@Override
	public void visualize() {
		// RENDER THE CHART
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(this.analysisType, (CategoryDataset) dataset,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.west.add(chartPanel);

	}

}
