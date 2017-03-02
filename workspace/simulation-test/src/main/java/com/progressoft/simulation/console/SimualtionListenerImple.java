package com.progressoft.simulation.console;

import com.progressoft.jip.dao.SimualtionDao;
import com.progressoft.jip.dao.SimulationImpleDao;
import com.progressoft.jip.dao.VehicleStatesImpleDao;
import com.progressoft.jip.dao.VehicleStatusDoa;
import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.framework.VehicleStatus;

public class SimualtionListenerImple implements SimulationStateListener {

	private SimualtionDao simulationDao;
	private VehicleStatusDoa vehicleStatesDao;
	private VehicleStatus vehicleStatus;

	public SimualtionListenerImple(SimulationImpleDao simulationImpleDao, VehicleStatesImpleDao vehicleStatesImpleDao) {
		this.simulationDao = simulationImpleDao;
		this.vehicleStatesDao = vehicleStatesImpleDao;
	}

	@Override
	public void simulationStarted(SimulationState state) {
		vehicleStatesDao.create(vehicleStatus);

	}

	@Override
	public void statusChanged(SimulationState state) {
		Iterable<? extends VehicleStatus> statuses = state.getVehicleStatuses();
		vehicleStatesDao.update(vehicleStatus);
	}

}
