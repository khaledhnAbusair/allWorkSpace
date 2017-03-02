package com.progressoft.jip.simulation.implemntation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.progressoft.jip.framework.VehicleStatus.Status;

public class WaitingSimualtion implements Runnable {
	private SimualtionImple simualtionImple;

	public WaitingSimualtion(SimualtionImple simualtionImple) {
		this.simualtionImple = simualtionImple;
	}

	@Override
	public void run() {
		List<VehicleStatusImple> vehicleStatusList = simualtionImple.getVehicleStatusList();

		for (VehicleStatusImple vehicleStatus : vehicleStatusList) {
			Duration calculateArriveTime = ArrivalTime(vehicleStatus);
			try {
				Thread.sleep(calculateArriveTime.toMillis() < 0 ? 0 : calculateArriveTime.toMillis());
			} catch (InterruptedException e) {
			}
			changeToWaitStatus(vehicleStatus);
			initializeParkCar(vehicleStatus);
		}

	}

	private Duration ArrivalTime(VehicleStatusImple vehicleStatus) {
		Duration arriveAfter = vehicleStatus.vehicle().arriveAfter();
		LocalDateTime startSimualtionTime = simualtionImple.getStartSimualtionTime();
		Duration currentTime = Duration.between(startSimualtionTime, LocalDateTime.now());
		Duration calculateArriveTime = arriveAfter.minus(currentTime);
		return calculateArriveTime;
	}

	private void notifyListener() {
		simualtionImple.notifyListenerOfChange();
	}

	private void initializeParkCar(VehicleStatusImple vehicleStatus) {
		ParkingSimualtionVehicle parkSimualtionVehicle;
		parkSimualtionVehicle = new ParkingSimualtionVehicle(vehicleStatus, simualtionImple.getSimState(),
				simualtionImple);
		new Thread(parkSimualtionVehicle).start();
	}

	private void changeToWaitStatus(VehicleStatusImple vehicleStatus) {
		vehicleStatus.setStatus(Status.WAITING);
		notifyListener();
	}

}
