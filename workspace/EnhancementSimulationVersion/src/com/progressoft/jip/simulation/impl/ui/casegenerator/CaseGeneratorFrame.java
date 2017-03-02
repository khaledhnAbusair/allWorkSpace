package com.progressoft.jip.simulation.impl.ui.casegenerator;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.simulation.impl.ui.swinggenerator.SwingCaseGenerator;
import com.progressoft.jip.simulation.implemntation.SimualtionImple;

public class CaseGeneratorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public CaseGeneratorFrame() {
		super("Case Generator");
		Simulation simulation = new SimualtionImple();
		SwingCaseGenerator caseGenerator = new SwingCaseGenerator(simulation);

		setLayout(new FlowLayout());

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(caseGenerator);
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(() -> {
			CaseGeneratorFrame frame = new CaseGeneratorFrame();

			frame.pack();
			frame.setResizable(false);
			frame.setVisible(true);
		});
	}
}
