package com.progressoft.jip.simulation.impl.ui;

import java.awt.FlowLayout;
import java.nio.channels.IllegalSelectorException;
import java.util.Iterator;
import java.util.ServiceLoader;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.progressoft.jip.simulation.SimulationFactory;
import com.progressoft.jip.simulation.impl.ui.swinggenerator.SwingCaseGenerator;
import com.progressoft.jip.simulation.warehouse.SimulationFactoryImp;

public class CaseGeneratorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5721078389993979078L;

	public CaseGeneratorFrame() {
		super("Case Generator");
		// TODO replace this
		SimulationFactory simulationFactory = getSimulatuionFactory();
		SwingCaseGenerator caseGenerator = new SwingCaseGenerator(simulationFactory);

		setLayout(new FlowLayout());

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(caseGenerator);
	}
	
	public static SimulationFactory getSimulatuionFactory(){
		ServiceLoader<SimulationFactory> loader = ServiceLoader.load(SimulationFactory.class);
		Iterator<SimulationFactory> iterator = loader.iterator();
		if(iterator.hasNext()){
			return iterator.next();
		}
		throw new IllegalStateException("no implementations found in class path");
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
