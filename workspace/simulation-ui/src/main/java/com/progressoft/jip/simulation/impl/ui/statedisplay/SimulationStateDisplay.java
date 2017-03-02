/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui.statedisplay;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalTime;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.simulation.Simulation;
import com.progressoft.jip.simulation.SimulationState;
import com.progressoft.jip.simulation.SimulationStateListener;
import com.progressoft.jip.simulation.impl.ui.TabularComponent;


/**
 * @author phi01tech
 *
 */
public class SimulationStateDisplay extends JDialog implements SimulationStateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel occupiedLabel = new JLabel("0");
	private SimulationState state;
	private AbstractTableModel tableModel;
	private JLabel startTimeField;
	private JLabel lastUpdateTimeField;

	public SimulationStateDisplay(Simulation simulation) {
		setTitle("Simulation Status");
		setLayout(new BorderLayout());
		state = simulation.getCurrentState();
		addForm();
		addStatusTable();
		simulation.addStateListener(this);
	
	}

	private void addStatusTable() {
		tableModel = new TableModel(state);
		JTable statusesTable = new JTable(tableModel);
		add(new JScrollPane(statusesTable), BorderLayout.CENTER);
	}

	private void addForm() {
		JPanel panel = new JPanel();
		startTimeField = new JLabel(this.state.startTime().toLocalTime().toString());
		lastUpdateTimeField = new JLabel(LocalTime.now().toString());
		panel.setLayout(new FlowLayout());
		
		TabularComponent tabular = new TabularComponent(panel);
		
		tabular.addJComponentDarkGrayBorder(new JLabel("Occupied lots: "));
		tabular.addJComponentDarkGrayBorder(occupiedLabel);
		panel.add(new JLabel("Start Time: "));
		tabular.addJComponentDarkGrayBorder(startTimeField);
		panel.add(new JLabel("Last update: "));
		tabular.addJComponentDarkGrayBorder(lastUpdateTimeField);
		
		add(panel, BorderLayout.NORTH);

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

}
