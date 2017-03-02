/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui.simstate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalTime;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.simulation.impl.ui.casegenerator.TabluarComponent;

/**
 * @author phi01tech
 *
 */
public class SimulationStateDisplay extends JDialog implements SimulationStateListener {

	private static final long serialVersionUID = -400847586800780366L;
	private JLabel occupiedLabel = new JLabel("0");
	private AbstractTableModel tableModel;
	private JLabel lastUpdateTimeField;
	private SimulationState state;
	private JLabel startTimeField;

	public SimulationStateDisplay(Simulation simulation) {
		initializeInitialState(simulation);
	}

	@Override
	public void simulationStarted(SimulationState state) {
		occupiedLabel.setText(state.occupiedParkinglots() + "");
		lastUpdateTimeField.setText(LocalTime.now().toString());
		tableModel.fireTableDataChanged();
	}

	@Override
	public void statusChanged(SimulationState state) {
		occupiedLabel.setText(state.occupiedParkinglots() + "");
		lastUpdateTimeField.setText(LocalTime.now().toString());
		tableModel.fireTableDataChanged();
	}

	private void initializeInitialState(Simulation simulation) {
		setTitle();
		setLayout(new BorderLayout());
		getCurrentState(simulation);
		addStatusAndForm();
		addListener(simulation);

	}

	private void setTitle() {
		setTitle("Simulation Status");
	}

	private void addListener(Simulation simulation) {
		simulation.addStateListener(this);
	}

	private void addStatusAndForm() {
		addForm();
		tableModel = new AddStatuesTable(state);
		JTable statusesTable = new JTable(tableModel);
		add(new JScrollPane(statusesTable), BorderLayout.CENTER);
	}

	private void getCurrentState(Simulation simulation) {
		state = simulation.getCurrentState();
	}

	private void addForm() {

		JPanel panel = new JPanel();
		startTimeField = new JLabel(this.state.startTime().toLocalTime().toString());
		lastUpdateTimeField = new JLabel(LocalTime.now().toString());
		panel.setLayout(new FlowLayout());

		TabluarComponent tabluarComponent = new TabluarComponent();

		tabluarComponent.addComp(panel, new JLabel("Occupied lots:" ), 0, 0);
		tabluarComponent.addComp(panel, occupiedLabel, 0, 0);
		tabluarComponent.addComp(panel, new JLabel(" | Start Time: "), 0, 0);
		tabluarComponent.addComp(panel, startTimeField, 0, 0);
		tabluarComponent.addComp(panel, new JLabel("| Last Update Time: "), 0, 1);
		tabluarComponent.addComp(panel, lastUpdateTimeField, 0, 0);

		add(panel, BorderLayout.NORTH);
	}

}
