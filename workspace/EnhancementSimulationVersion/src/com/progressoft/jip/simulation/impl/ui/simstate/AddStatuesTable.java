package com.progressoft.jip.simulation.impl.ui.simstate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.VehicleStatus;
import com.progressoft.jip.framework.VehicleStatus.Status;

@SuppressWarnings("serial")
public class AddStatuesTable extends AbstractTableModel {
	private SimulationState simulationState;
	private Map<Integer, String> columnName = new HashMap<>();
	private Map<Integer, Object> valueAt = new HashMap<>();

	public AddStatuesTable(SimulationState state) {
		this.simulationState = state;
	}

	public String getColumnName(int column) {

		columnName.put(0, "ID");
		columnName.put(1, "Type");
		columnName.put(2, "Task");
		columnName.put(3, "Arrive Time");
		columnName.put(4, "Status");
		columnName.put(5, "Waiting start");
		columnName.put(6, "Parking time");
		columnName.put(7, "leaving time");
		return columnName.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		List<VehicleStatus> casesAsList = simulationState.getVehicleStatusesAsStream().collect(Collectors.toList());
		VehicleStatus vehicleCase = casesAsList.get(rowIndex);

		valueAt.put(0, vehicleCase.vehicle().identifier());
		valueAt.put(1, vehicleCase.vehicle().type());
		valueAt.put(2, vehicleCase.vehicle().task());
		valueAt.put(3, vehicleCase.arrivalTime());
		valueAt.put(4, vehicleCase.status());
		valueAt.put(5, vehicleCase.statusTime(Status.WAITING));
		valueAt.put(6, vehicleCase.statusTime(Status.PARKED));
		valueAt.put(7, vehicleCase.statusTime(Status.LEFT));

		return valueAt.get(columnIndex);
	}

	@Override
	public int getRowCount() {
		return (int) simulationState.getVehicleStatusesAsStream().count();

	}

	@Override
	public int getColumnCount() {
		return 7;
	}

}
