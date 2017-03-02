package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.SimulationState;

public class SimulationStateImple implements SimulationState {

	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();
	private LocalDateTime startSimualtionTime;
	private int occupiedParkinglots = 0;
	private SimulationCase simCase;
	private Object parkingMonitor = new Object();

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
	public int occupiedParkinglots() {
		synchronized (parkingMonitor) {

			return occupiedParkinglots;
		}
	}

	public void incrementOccupiedParkingLots() {
		synchronized (parkingMonitor) {
			while (!hasEmptyLots()) {
				try {
					parkingMonitor.wait();
				} catch (InterruptedException e) {
				}
			}
			occupiedParkinglots++;

		}
	}

	public void decrementOccupiedParkingLots() {
		synchronized (parkingMonitor) {
			occupiedParkinglots--;
			parkingMonitor.notifyAll();

		}
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
