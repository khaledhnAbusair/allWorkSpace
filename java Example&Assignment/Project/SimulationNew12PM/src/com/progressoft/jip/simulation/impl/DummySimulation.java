/**
 * 
 */
package com.progressoft.jip.simulation.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.progressoft.jip.simulation.Simulation;
import com.progressoft.jip.simulation.SimulationCase;
import com.progressoft.jip.simulation.SimulationState;
import com.progressoft.jip.simulation.SimulationStateListener;
import com.progressoft.jip.simulation.VehicleCase;
import com.progressoft.jip.simulation.VehicleStatus;

/**
 * @author phi01tech
 *
 */
public class DummySimulation implements Simulation {

	private SimulationStateListener listener = new SimulationStateListener() {
		
		@Override
		public void statusChanged(SimulationState state) {
			
		}
		
		@Override
		public void simulationStarted(SimulationState state) {
			
		}
	};
	private SimulationCase simCase;
	private LocalDateTime startTime;

	@Override
	public void start(SimulationCase simCase) {
		this.simCase = simCase;
		startTime = LocalDateTime.now();
		new Thread(new Trigger()).start();
	}

	@Override
	public SimulationState getCurrentState() {
		return new DummyState();
	}

	@Override
	public void addStateListener(SimulationStateListener listener) {
		this.listener = listener;
	}

	private class Trigger implements Runnable {

		@Override
		public void run() {
			listener.simulationStarted(new DummyState());
			Random random = new Random();
			while (true) {
				try {
					Thread.sleep(random.nextInt(20) * 1000);
					listener.statusChanged(new DummyState());
				} catch (InterruptedException e) {
					break;
				}
			}
		}

	}

	private class DummyState implements SimulationState {

		@Override
		public SimulationCase getCase() {
			return simCase;
		}

		@Override
		public int occupiedParkinglots() {
			return simCase.parkingLotsCount();
		}

		@Override
		public LocalDateTime startTime() {
			return startTime;
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
		public Iterable<VehicleStatus> getVehicleStatuses() {
			Iterable<VehicleCase> cases = simCase.vehicleCases();
			List<VehicleStatus> statues = new ArrayList<>();
			Random random = new Random();

			for (VehicleCase vehicleCase : cases) {
				statues.add(new VehicleStatus() {

					@Override
					public VehicleCase vehicle() {
						return vehicleCase;
					}

					@Override
					public LocalDateTime statusTime(Status status) {
						return LocalDateTime.now();
					}

					@Override
					public Status status() {
						int rand = random.nextInt(3);
						return Status.values()[rand];
					}

					@Override
					public LocalDateTime arrivalTime() {
						return LocalDateTime.now();
					}
				});
			}
			return statues;
		}

	}

}
