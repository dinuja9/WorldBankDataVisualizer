package statsVisualiser.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class SeriesGraph {
	
	public SeriesGraph(JPanel west, String analysisType, String country, int startDate, int endDate) {
		if(analysisType.equals("3 series")) {
			create3Series(west, "can", 2001, 2018);
		}
		else if(analysisType.equals("2 series")) {
			create2Series(west, "can", 2001, 2018);
		}
	}
	
	private void create2Series(JPanel west, String country, int startDate, int endDate) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", (startDate)-1, endDate, country);
		airPolution.fetchData();
		
		TimeSeries series2 = new TimeSeries("Annual % change of forest area"); 
		GetData forestArea = new GetData("AG.LND.FRST.ZS", (startDate)-1, endDate, country);
		forestArea.fetchData();	
		
		// ADDING THE DATA ON THE DATASET FOR SERIES 1 AND 2
		//percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100
		
		for(int i = airPolution.year.size()-1; i > 1; i--) {
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series1.add(new Year(airPolution.year.get(i)), percentChange);
			}
			System.out.println("air pol =>" + airPolution.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		dataset.addSeries(series1);
		
		for(int i = forestArea.year.size()-1; i > 1; i--) {
			double CurrentYearValue = forestArea.valueOfYear.get(i);
			double LastYearValue = forestArea.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series2.add(new Year(forestArea.year.get(i)), percentChange);
			}
			System.out.println("forest area =>" + forestArea.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		dataset.addSeries(series2);
		
		
		
		// RENDER THE CHART
		JFreeChart chart = ChartFactory.createXYLineChart("Analysis 2", "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		
		
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
		west.add(chartPanel);
		
		
	}

	private void create3Series(JPanel west, String country, int startDate, int endDate)  {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries series1 = new TimeSeries("Annual % change of CO2 emissions"); 
		GetData CO2Emissions = new GetData("EN.ATM.CO2E.PC", startDate-1, endDate, country);
		CO2Emissions.fetchData();

		TimeSeries series2 = new TimeSeries("Annual % change of energy use"); 
		GetData NRG = new GetData("EG.USE.PCAP.KG.OE", startDate-1, endDate, country);
		NRG.fetchData();
		
		TimeSeries series3 = new TimeSeries("Annual % change of PM2.5 air pollution");
		GetData airPolution = new GetData("EN.ATM.PM25.MC.M3", startDate-1, endDate, country);
		airPolution.fetchData();

		// ADDING THE DATA ON THE DATASET FOR SERIES 1 TO 3
		//percent change = (CurrentYearValue - LastYearValue)/LastYearValue * 100
		for(int i = CO2Emissions.year.size()-1; i > 1; i--) {
			double CurrentYearValue = CO2Emissions.valueOfYear.get(i);
			double LastYearValue = CO2Emissions.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange)) {
				continue;
			}
			else {
				series1.add(new Year(CO2Emissions.year.get(i)), percentChange);
				System.out.println("CO2 emissions =>" + CO2Emissions.year.get(i) + " : " + percentChange);
			}
		}
		dataset.addSeries(series1);
		
		for(int i = NRG.year.size()-1; i > 1; i--) {
			double CurrentYearValue = NRG.valueOfYear.get(i);
			double LastYearValue = NRG.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange)) {
				continue;
			}
			else {
			series2.add(new Year(NRG.year.get(i)), percentChange);
			System.out.println("NRG =>" + NRG.year.get(i) + " : " +percentChange);
			}
		}
		dataset.addSeries(series2);
		
		for(int i = airPolution.year.size()-1; i > 1; i--) {
			double CurrentYearValue = airPolution.valueOfYear.get(i);
			double LastYearValue = airPolution.valueOfYear.get(i-1);
			double percentChange = (CurrentYearValue - LastYearValue)/LastYearValue*100;
			if(Double.isNaN(percentChange) || Double.isInfinite(percentChange) || CurrentYearValue==0.0) {
				continue;
			}
			else {
			series3.add(new Year(airPolution.year.get(i)), percentChange);
			}
			System.out.println("air pol =>" + airPolution.year.get(i) + " : "+LastYearValue +"=>"+CurrentYearValue);
			
		}
		dataset.addSeries(series3);
		
		// RENDER THE CHART
		JFreeChart chart = ChartFactory.createXYLineChart("Analysis 1", "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		
		
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
		west.add(chartPanel);
	}
	
}
