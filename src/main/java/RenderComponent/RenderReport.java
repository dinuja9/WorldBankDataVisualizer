package RenderComponent;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is the render class for the report. This is being implemented with the RenderInterface class.   
 */
public class RenderReport implements RenderInterface {

	private JPanel west;
	String analysisType;
	ArrayList<String> dataReport;

	/**
	 * Set the report with the data and type of analysis.
	 * @param dataset
	 * @param west
	 * @param analysisType
	 */
	public RenderReport(JPanel west, String analysisType, ArrayList<String> obj) {
		this.west = west;
		this.analysisType = analysisType;
		this.dataReport = obj;
	}

	/**
	 * This method adds the selected data and type of analysis to a new report.
	 */
	public void visualize() {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		String reportMessage = analysisType + "\n";
		for (int i = 0; i < analysisType.length() / 1.3; i++) {
			reportMessage += "=";
		}
		reportMessage += "\n";
		for (String s : dataReport) {
			reportMessage += s + "\n";
		}

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.west.add(outputScrollPane);
	}
}
