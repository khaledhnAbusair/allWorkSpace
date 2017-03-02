package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.framework.VehicleStatus.Status;

public class ParkedCar implements Runnable {
	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();
	private LocalDateTime startSimualtionTime;
	private SimulationStateImple simState;
	private SimulationStateListener listener;

	public ParkedCar(List<VehicleStatusImple> vehicleStatusList, LocalDateTime startSimualtionTime,
			SimulationStateImple simState) {
		this.vehicleStatusList = vehicleStatusList;
		this.startSimualtionTime = startSimualtionTime;
		this.simState = simState;

	}

	@Override
	public void run() {
		listener.simulationStarted(simState);
		while (isEmptyList()) {
			for (VehicleStatusImple vehicleStatus : vehicleStatusList) {
				changeToWaitStatus(vehicleStatus);
				changeToParkedStatus(vehicleStatus);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

		}
	}

	private void changeToParkedStatus(VehicleStatusImple vehicleStatus) {
		while (isEmptyLots() && isStatusWait(vehicleStatus)) {
			setNewArrivalTime(vehicleStatus);
			vehicleStatus.setStatus(Status.PARKED);
			simState.incrementOccupiedParkingLots();
			listener.statusChanged(simState);
			new Thread(new LeftCar(vehicleStatus, simState, listener, Status.PARKED)).start();
		}
	}

	private void setNewArrivalTime(VehicleStatusImple vehicleStatus) {
		vehicleStatus.setArrivalTime(LocalDateTime.now());
	}

	private void changeToWaitStatus(VehicleStatusImple vehicleStatus) {
		long vehicleArriveTime = vehicleStatus.vehicle().arriveAfter().getSeconds();
		LocalDateTime arriveTime = startSimualtionTime.plusSeconds(vehicleArriveTime);
		isStatusArrived(vehicleStatus, arriveTime);
	}

	private void isStatusArrived(VehicleStatusImple vehicleStatus, LocalDateTime arriveTime) {
		if (arriveTime(arriveTime) && isStatusNotArrived(vehicleStatus)) {
			setNewArrivalTime(vehicleStatus);
			vehicleStatus.setStatus(Status.WAITING);
			listener.statusChanged(simState);
		}
	}

	private boolean isStatusNotArrived(VehicleStatusImple vehicleStatus) {
		return vehicleStatus.isInStatus(Status.NOTARRIVED);
	}

	private boolean arriveTime(LocalDateTime arriveTime) {
		return LocalDateTime.now().compareTo(arriveTime) >= 0;
	}

	private boolean isStatusWait(VehicleStatusImple vehicleStatus) {
		return vehicleStatus.isInStatus(Status.WAITING);
	}

	private boolean isEmptyLots() {
		return simState.hasEmptyLots();
	}

	private boolean isEmptyList() {
		return !vehicleStatusList.isEmpty();
	}

	public void setListner(SimulationStateListener listener) {
		this.listener = listener;
	}

}
