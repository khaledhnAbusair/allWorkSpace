package com.progressoft.jip.simulation.impl.ui.statedisplay;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.simulation.SimulationState;
import com.progressoft.jip.simulation.VehicleStatus;

/**
 * @author u618
 *
 */
class TableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1044469397949783806L;
	private SimulationState state;

	public TableModel(SimulationState state) {
		this.state = state;
	}

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
		}

		return null;
	}

	@Override
	public int getRowCount() {
		return (int) state.getVehicleStatusesAsStream().count();
	}

	@Override
	public int getColumnCount() {
		return 5;
}
}