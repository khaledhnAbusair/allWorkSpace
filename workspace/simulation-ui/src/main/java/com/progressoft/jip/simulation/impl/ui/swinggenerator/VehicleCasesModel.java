package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.progressoft.jip.simulation.VehicleCase;

/**
 * @author u618
 *
 */
class VehicleCasesModel  extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VehicleCase> vehicleCases;

	public VehicleCasesModel(List<VehicleCase> vehicleCases) {
		this.vehicleCases = vehicleCases;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Type";
		case 2:
			return "Task";
		case 3:
			return "Arrive After";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<VehicleCase> casesAsList = new ArrayList<>(vehicleCases);
		VehicleCase vehicleCase = casesAsList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return vehicleCase.identifier();
		case 1:
			return vehicleCase.type();
		case 2:
			return vehicleCase.task();
		case 3:
			return vehicleCase.arriveAfter();
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return vehicleCases.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

}
