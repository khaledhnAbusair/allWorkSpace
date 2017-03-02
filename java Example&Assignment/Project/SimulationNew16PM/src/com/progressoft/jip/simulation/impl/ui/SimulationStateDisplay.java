/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.framework.VehicleStatus;
import com.progressoft.jip.framework.VehicleStatus.Status;

/**
 * @author phi01tech
 *
 */
public class SimulationStateDisplay extends JDialog implements SimulationStateListener {

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
		tableModel = new AbstractTableModel() {

			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "ID";
				case 1:
					return "Type";
				case 2:
					return "Task";
				case 3:
					return "Arrive Time";
				case 4:
					return "Status";
				case 5:
					return "Waiting start";
				case 6:
					return "Parking time";
				case 7:
					return "leaving time";
				}
				return null;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				List<VehicleStatus> casesAsList = state.getVehicleStatusesAsStream().collect(Collectors.toList());
				VehicleStatus vehicleCase = casesAsList.get(rowIndex);
				switch (columnIndex) {
				case 0:
					return vehicleCase.vehicle().identifier();
				case 1:
					return vehicleCase.vehicle().type();
				case 2:
					return vehicleCase.vehicle().task();
				case 3:
					return vehicleCase.arrivalTime();
				case 4:
					return vehicleCase.status();
				case 5:
					return vehicleCase.statusTime(Status.WAITING);
				case 6:
					return vehicleCase.statusTime(Status.PARKED);
				case 7:
					return vehicleCase.statusTime(Status.LEFT);
				}

				return null;
			}

			@Override
			public int getRowCount() {
				return (int) state.getVehicleStatusesAsStream().count();
			}

			@Override
			public int getColumnCount() {
				return 7;
			}
		};
		JTable statusesTable = new JTable(tableModel);
		add(new JScrollPane(statusesTable), BorderLayout.CENTER);
	}

	private void addForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		panel.add(new JLabel("Occupied lots: "));
		occupiedLabel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.add(occupiedLabel);

		panel.add(new JLabel("Start Time: "));
		startTimeField = new JLabel(this.state.startTime().toLocalTime().toString());
		startTimeField.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.add(startTimeField);

		panel.add(new JLabel("Last update: "));
		lastUpdateTimeField = new JLabel(LocalTime.now().toString());
		lastUpdateTimeField.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.add(lastUpdateTimeField);
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
