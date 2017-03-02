package com.progressoft.jip.simulation.impl.ui.implemn;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.framework.VehicleCase;

@SuppressWarnings("serial")
public class VehicleCasesModel extends AbstractTableModel {

	private List<VehicleCase> vehicleCases;

	public VehicleCasesModel(List<VehicleCase> vehicleCases) {
		this.vehicleCases = vehicleCases;
	}

	@Override
	public String getColumnName(int column) {
		String[] comlomnNames = { "ID", "Type", "Task", "Arrive Time" };
		return comlomnNames[column];
	}

	@Override
	public int getRowCount() {
		return vehicleCases.size();

	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		List<VehicleCase> casesAsList = new ArrayList<>(vehicleCases);
		VehicleCase vehicleCase = casesAsList.get(rowIndex);
		Object[] valueAt = { vehicleCase.identifier(), vehicleCase.type(), vehicleCase.task(),
				vehicleCase.arriveAfter() };

		return valueAt[columnIndex];

	}

}
