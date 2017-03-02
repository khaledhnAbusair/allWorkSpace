package com.progressoft.jip.simulation.implemntation;

import java.time.Duration;
import java.time.LocalDateTime;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.framework.VehicleStatus.Status;

public class LeftCar implements Runnable {
	private SimulationStateListener listener;
	private SimulationStateImple simState;
	VehicleStatusImple vehicleStatus;
	private SimulationCase simCase;
	private Status status;

	public LeftCar(VehicleStatusImple vehicleStatus, SimulationStateImple simState, SimulationStateListener listener,
			Status status) {
		this.vehicleStatus = vehicleStatus;
		this.simCase = simState.getCase();
		this.simState = simState;
		this.listener = listener;
		this.status = status;

	}

	@Override
	public void run() {
		while (vehicleStatus.isInStatus(Status.PARKED)) {

			Task getVehicleTask = vehicleStatus.vehicle().task();
			Type getVehicleType = vehicleStatus.vehicle().type();
			Duration taskDuration = simCase.getTaskDuration(getVehicleType, getVehicleTask);
			LocalDateTime setStatus = vehicleStatus.statusTime(status);
			Duration currentStatusTime = Duration.between(setStatus, LocalDateTime.now());

			if (currentStatusTime.compareTo(taskDuration) >= 0) {
				vehicleStatus.setStatus(Status.LEFT);
				vehicleStatus.setArrivalTime(LocalDateTime.now());
				simState.decrementOccupiedParkingLots();
				listener.statusChanged(simState);

			}

		}

	}

}
