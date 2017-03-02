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
	VehicleStatusImple vehicleStatus;
	private SimulationCase simCase;
	private Status status;

	public LeftCar(VehicleStatusImple vehicleStatus, SimulationStateImple simState, Status status,
			SimualtionImple simualtionImple) {
		this.vehicleStatus = vehicleStatus;
		this.simualtionImple = simualtionImple;
		this.simCase = simState.getCase();
		this.simState = simState;
		this.status = status;

	}

	@Override
	public void run() {
		// TODO I will always be parked
		Task getVehicleTask = vehicleStatus.vehicle().task();
		Type getVehicleType = vehicleStatus.vehicle().type();
		Duration taskDuration = simCase.getTaskDuration(getVehicleType, getVehicleTask);
		LocalDateTime setStatus = vehicleStatus.statusTime(status);
		Duration currentStatusTime = Duration.between(setStatus, LocalDateTime.now());

		while (vehicleStatus.isInStatus(Status.PARKED)) {

			if (currentStatusTime.compareTo(taskDuration) >= 0) {
				setStatusLeft();
				serArrivalTime();
				decrementParkingLots();
				notifyListener();

			} else {
				try {
					Thread.sleep(taskDuration.getSeconds());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

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
