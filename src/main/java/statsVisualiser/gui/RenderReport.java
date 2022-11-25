package statsVisualiser.gui;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RenderReport implements RenderInterface {
	 
	private JPanel west;
	String analysisType;
	ArrayList<String> dataReport;
	
	public RenderReport(JPanel west, String analysisType, ArrayList<String> obj) {
		this.west = west;
		this.analysisType = analysisType;
		this.dataReport = obj;
	}

	@Override
	public void visualize() {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		// Adapter pattern used here to write the report for us:
		ReportInterface repo = new ReportAdapter();
		String reportMessage = repo.writeReport(this.dataReport, this.analysisType);	

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.west.add(outputScrollPane);
	}

}
