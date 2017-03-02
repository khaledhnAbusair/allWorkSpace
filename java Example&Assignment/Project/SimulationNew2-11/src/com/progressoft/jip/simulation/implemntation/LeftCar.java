package com.progressoft.jip.simulation.implemntation;

import java.time.Duration;
import java.time.LocalDateTime;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.framework.VehicleStatus.Status;

public class LeftCar implements Runnable {
	private SimualtionImple simualtionImple;
	private SimulationStateImple simState;
	private VehicleStatusImple vehicleStatus;
	private SimulationCase simCase;

	public LeftCar(VehicleStatusImple vehicleStatus, SimulationStateImple simState, SimualtionImple simualtionImple) {
		this.vehicleStatus = vehicleStatus;
		this.simualtionImple = simualtionImple;
		this.simCase = simState.getCase();
		this.simState = simState;
	}

	@Override
	public void run() {
		synchronized (GaragesGuard.getGuardGarages()) {
			Task getVehicleTask = vehicleStatus.vehicle().task();
			Type getVehicleType = vehicleStatus.vehicle().type();
			Duration taskDuration = simCase.getTaskDuration(getVehicleType, getVehicleTask);
			try {
				GaragesGuard.getGuardGarages().wait(taskDuration.getSeconds() * 1000);
			} catch (InterruptedException e) {
			}
			setStatusLeft();
			serArrivalTime();
			decrementParkingLots();
			notifyListener();
		}
	}

	private void notifyListener() {
		simualtionImple.notifyListenerOfChange();
	}

	private void decrementParkingLots() {
		simState.decrementOccupiedParkingLots();
	}

	private void serArrivalTime() {
		vehicleStatus.setArrivalTime(LocalDateTime.now());
	}

	private void setStatusLeft() {
		vehicleStatus.setStatus(Status.LEFT);
	}

}
