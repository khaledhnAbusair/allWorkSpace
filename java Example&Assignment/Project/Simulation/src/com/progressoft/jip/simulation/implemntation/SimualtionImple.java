package com.progressoft.jip.simulation.implemntation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.progressoft.jip.simulation.framework.Simulation;
import com.progressoft.jip.simulation.framework.SimulationCase;
import com.progressoft.jip.simulation.framework.SimulationState;
import com.progressoft.jip.simulation.framework.SimulationStateListener;
import com.progressoft.jip.simulation.framework.VehicleCase;
import com.progressoft.jip.simulation.framework.VehicleStatus.Status;

public class SimualtionImple implements Simulation {
	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();
	private LocalDateTime startSimualtionTime;
	private SimulationStateImple simState;
	private SimulationCase simCase;
	private Object monitor = new Object();

	private SimulationStateListener listener = new SimulationStateListener() {

		@Override
		public void statusChanged(SimulationState state) {

		}

		@Override
		public void simulationStarted(SimulationState state) {

		}
	};

	@Override
	public void start(SimulationCase simCase) {
		this.simCase = simCase;
		this.startSimualtionTime = LocalDateTime.now();
		Iterator<VehicleCase> iterator = simCase.vehicleCases().iterator();
		while (iterator.hasNext()) {
			vehicleStatusList.add(new VehicleStatusImple(iterator.next(), LocalDateTime.now(), Status.NOTARRIVED));
		}
		simState = new SimulationStateImple(simCase, startSimualtionTime, vehicleStatusList);

		new Thread(new TriggerThread()).start();
	}

	@Override
	public SimulationState getCurrentState() {

		return new SimulationStateImple(simCase, startSimualtionTime, vehicleStatusList);

	}

	@Override
	public void addStateListener(SimulationStateListener listener) {
		this.listener = listener;
	}

	public class TriggerThread implements Runnable {

		@Override
		public void run() {

			listener.simulationStarted(simState);

			while (!vehicleStatusList.isEmpty()) {

				for (VehicleStatusImple vehicleStatus : vehicleStatusList) {

					LocalDateTime arriveTime = startSimualtionTime
							.plusSeconds(vehicleStatus.vehicle().arriveAfter().getSeconds());

					if (LocalDateTime.now().compareTo(arriveTime) >= 0 && vehicleStatus.isInStatus(Status.NOTARRIVED)) {
						vehicleStatus.setArrivalTime(LocalDateTime.now());
						vehicleStatus.setStatus(Status.WAITING);
						listener.statusChanged(simState);
					}

					while (simState.hasEmptyLots() && vehicleStatus.isInStatus(Status.WAITING)) {
						synchronized (monitor) {

							vehicleStatus.setArrivalTime(LocalDateTime.now());
							vehicleStatus.setStatus(Status.PARKED);
							simState.incrementOccupiedParkingLots();
							listener.statusChanged(simState);

							new Thread(new TiggerThread2(vehicleStatus)).start();
						}
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

			}
		}

		public class TiggerThread2 implements Runnable {
			VehicleStatusImple vehicleStatus;

			public TiggerThread2(VehicleStatusImple vehicleStatus) {
				this.vehicleStatus = vehicleStatus;
			}

			@Override
			public void run() {
				while (vehicleStatus.isInStatus(Status.PARKED)) {

					Duration taskDuration = simCase.getTaskDuration(vehicleStatus.vehicle().type(),
							vehicleStatus.vehicle().task());
					Duration currentTime = Duration.between(vehicleStatus.statusTime(Status.PARKED),
							LocalDateTime.now());

					if (currentTime.compareTo(taskDuration) >= 0) {
						synchronized (monitor) {
							vehicleStatus.setStatus(Status.LEFT);
							vehicleStatus.setArrivalTime(LocalDateTime.now());
							simState.decrementOccupiedParkingLots();
							listener.statusChanged(simState);
						}

					}
				}

			}

		}

	}
}
