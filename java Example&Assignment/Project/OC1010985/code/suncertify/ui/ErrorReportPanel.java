package suncertify.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Panel which displays an error report.
 * 
 * @author Mohammad S. Abdellatif
 */
public class ErrorReportPanel extends JPanel {

	/**
	 * Construct a new error report panel.
	 * 
	 * @param failure
	 *            failure to display its details.
	 */
	public ErrorReportPanel(Throwable failure) {
		JLabel desc = new JLabel();
		JTextArea area = new JTextArea();
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		setLayout(new BorderLayout());

		desc.setText("A severe failure occurred, contact your administrator");
		add(desc, BorderLayout.PAGE_START);

		failure.printStackTrace(printWriter);

		area.setEditable(false);
		area.setColumns(40);
		area.setRows(10);
		area.setForeground(Color.BLACK);
		area.setBorder(new EtchedBorder());
		area.setText(writer.getBuffer().toString());
		area.setCaretPosition(0);

		add(new JScrollPane(area), BorderLayout.CENTER);
	}
}