package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.List;

import com.progressoft.jip.framework.VehicleStatus.Status;

public class ParkedCar implements Runnable {
	private SimualtionImple simualtionImple;

	public ParkedCar(SimualtionImple simualtionImple) {
		this.simualtionImple = simualtionImple;
	}

	@Override
	public void run() {
		List<VehicleStatusImple> vehicleStatusList = simualtionImple.getVehicleStatusList();
		while (allVehicleStatusLeft()) {
			for (VehicleStatusImple vehicleStatus : vehicleStatusList) {
				changeToWaitStatus(vehicleStatus);
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
		}
	}

	private void changeVehicleToParkedStatus(VehicleStatusImple vehicleStatus) {
		while (isEmptyLots() && isStatusWait(vehicleStatus)) {
			synchronized (GaragesGuard.getGuardGarages()) {
				setStatusParked(vehicleStatus);
				incrementParkingLots();
				notifyListener();
				initializeLeftCar(vehicleStatus);
			}
		}
	}

	private void setStatusParked(VehicleStatusImple vehicleStatus) {
		vehicleStatus.setStatus(Status.PARKED);
	}

	private void notifyListener() {
		simualtionImple.notifyListenerOfChange();
	}

	private void incrementParkingLots() {
		simualtionImple.getSimState().incrementOccupiedParkingLots();
	}

	private void setNewArrivalTime(VehicleStatusImple vehicleStatus) {
		vehicleStatus.setArrivalTime(LocalDateTime.now());
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
		return simualtionImple.getSimState().hasEmptyLots();
	}

	private void initializeLeftCar(VehicleStatusImple vehicleStatus) {
		LeftCar leftCar;
		leftCar = new LeftCar(vehicleStatus, simualtionImple.getSimState(), simualtionImple);
		new Thread(leftCar).start();
	}

	private void changeToWaitStatus(VehicleStatusImple vehicleStatus) {
		long vehicleArriveTime = vehicleStatus.vehicle().arriveAfter().getSeconds();
		LocalDateTime arriveTime = simualtionImple.getStartSimualtionTime().plusSeconds(vehicleArriveTime);
		checkIsStatusArrived(vehicleStatus, arriveTime);
		changeVehicleToParkedStatus(vehicleStatus);
	}

	private void checkIsStatusArrived(VehicleStatusImple vehicleStatus, LocalDateTime arriveTime) {
		if (arriveTime(arriveTime) && isStatusNotArrived(vehicleStatus)) {
			setNewArrivalTime(vehicleStatus);
			vehicleStatus.setStatus(Status.WAITING);
			notifyListener();
		}
	}

	private boolean allVehicleStatusLeft() {
		for (VehicleStatusImple vehicleStatusImple : simualtionImple.getVehicleStatusList()) {
			if (vehicleStatusImple.status() != Status.LEFT) {
				return true;
			}
		}
		return false;
	}
}
