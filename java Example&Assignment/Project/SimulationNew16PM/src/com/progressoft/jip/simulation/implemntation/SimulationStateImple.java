package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.SimulationState;

public class SimulationStateImple implements SimulationState {

	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();
	private LocalDateTime startSimualtionTime;
	private SimulationCase simCase;
	private int occupiedParkinglots = 0;

	public SimulationStateImple(SimulationCase simCase, LocalDateTime startSimualtionTime,
			List<VehicleStatusImple> vehicleStatusList) {
		this.startSimualtionTime = startSimualtionTime;
		this.vehicleStatusList = vehicleStatusList;
		this.simCase = simCase;

	}

	@Override
	public SimulationCase getCase() {
		return simCase;
	}

	@Override
	public synchronized int occupiedParkinglots() {
		return occupiedParkinglots;
	}

	public synchronized int incrementOccupiedParkingLots() {
		return occupiedParkinglots++;
	}

	public synchronized int decrementOccupiedParkingLots() {
		return occupiedParkinglots--;
	}

	@Override
	public LocalDateTime startTime() {
		return startSimualtionTime;

	}

	@Override
	public LocalDateTime completeTime() {
		return null;
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public Iterable<VehicleStatusImple> getVehicleStatuses() {
		return vehicleStatusList;
	}

}
