package statsVisualiser.gui;

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
	
	public RenderBar(Object dataset, JPanel west, String analysisType) {
		// TODO Auto-generated constructor stub
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	@Override
	public void visualize() {
		JFreeChart chart = ChartFactory.createBarChart(this.analysisType, "Data Types", "Values", (CategoryDataset) this.dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		this.west.add(chartPanel);

	}

}
