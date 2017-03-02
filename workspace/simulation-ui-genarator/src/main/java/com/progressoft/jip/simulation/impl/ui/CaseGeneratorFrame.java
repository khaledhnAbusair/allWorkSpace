package com.progressoft.jip.simulation.impl.ui;

import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.ServiceLoader;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationFactory;

public class CaseGeneratorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public CaseGeneratorFrame() {
		super("Case Generator");
		
		
		SimulationFactory orignal = getSimulationFactory();
		SimulationFactory factory=new SimulationFactory() {
			
			@Override
			public Simulation createSimulation() {
				return new AsyncSimulation(orignal.createSimulation());
			}
		};
		
		
		SwingCaseGenerator caseGenerator = new SwingCaseGenerator(factory);

		
		
		
		
		setLayout(new FlowLayout());

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(caseGenerator);
	}

	public static SimulationFactory getSimulationFactory() {

		ServiceLoader<SimulationFactory> loader = ServiceLoader.load(SimulationFactory.class);
		Iterator<SimulationFactory> iterator = loader.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}

		throw new IllegalStateException("No implemntations Found in ClassPath");
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
