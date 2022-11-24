package statsVisualiser.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class RenderReport implements RenderInterface {
	
	Object dataset; 
	private JPanel west;
	String analysisType;
	
	public RenderReport(Object dataset, JPanel west, String analysisType) {
		this.dataset = dataset;
		this.west = west;
		this.analysisType = analysisType;
	}

	@Override
	public void visualize() {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage = "";

		ReportInterface repo = new ReportAdapter();	
		AnalysisInterface a1 = repo.getAnalysisType(this.analysisType);
		Object dataset2 = repo.convertData(this.dataset, a1);		 		
		
	
		
		
//		reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n" + "Year 2018:\n"
//				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
//				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
//				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
//				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
//				+ "\tHospital Beds/1000 people => 2.77\n";		

//		report.setText(reportMessage);
//		JScrollPane outputScrollPane = new JScrollPane(report);
//		west.add(outputScrollPane);

	}

}
