package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.progressoft.jip.simulation.framework.SimulationCase;
import com.progressoft.jip.simulation.framework.SimulationState;

public class SimulationStateImple implements SimulationState {

	private SimulationCase simCase;
	private LocalDateTime startSimualtionTime;
	private int occupiedParkinglots = 0;
	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();

	public SimulationStateImple(SimulationCase simCase, LocalDateTime startSimualtionTime,
			List<VehicleStatusImple> vehicleStatusList) {
		this.simCase = simCase;
		this.startSimualtionTime = startSimualtionTime;
		this.vehicleStatusList = vehicleStatusList;

	}

	@Override
	public SimulationCase getCase() {
		return simCase;
	}

	@Override
	public int occupiedParkinglots() {
		return occupiedParkinglots;
	}

	public int incrementOccupiedParkingLots() {
		return occupiedParkinglots++;
	}

	public int decrementOccupiedParkingLots() {
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
