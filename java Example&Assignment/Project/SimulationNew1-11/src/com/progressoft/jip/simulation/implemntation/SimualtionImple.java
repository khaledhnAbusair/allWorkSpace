package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleStatus.Status;

public class SimualtionImple implements Simulation {
	private List<VehicleStatusImple> vehicleStatusList = new ArrayList<>();
	private LocalDateTime startSimualtionTime;
	private SimulationStateImple simState;
	private SimulationCase simCase;
	private ParkedCar parkedCar;

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
		initializeInitialVechicleList(simCase);
		initializeSimualtionState(simCase);
		parkedCar = new ParkedCar(this);
		startSimualtionParkInVechicle();
	}

	public SimulationStateImple getSimState() {
		return simState;
	}

	@Override
	public SimulationState getCurrentState() {

		return simState;

	}

	@Override
	public void addStateListener(SimulationStateListener listener) {
		this.listener = listener;

	}

	public void notifyListenerOfChange() {
		listener.statusChanged(simState);
	}

	public void notifyListenerOfStart() {
		listener.simulationStarted(simState);
	}

	public List<VehicleStatusImple> getVehicleStatusList() {
		Collections.sort(vehicleStatusList, (firstArriveTime, secondArriveTime) -> firstArriveTime.vehicle()
				.arriveAfter().compareTo(secondArriveTime.vehicle().arriveAfter()));
		return vehicleStatusList;
	}

	public LocalDateTime getStartSimualtionTime() {
		return startSimualtionTime;
	}

	public SimulationCase getSimCase() {
		return simCase;
	}

	private void startSimualtionParkInVechicle() {
		new Thread(parkedCar).start();
	}

	private void initializeSimualtionState(SimulationCase simCase) {
		simState = new SimulationStateImple(simCase, startSimualtionTime, vehicleStatusList);
	}

	private void initializeInitialVechicleList(SimulationCase simCase) {
		Iterator<VehicleCase> iteratorVechicleCases = simCase.vehicleCases().iterator();
		while (iteratorVechicleCases.hasNext()) {
			vehicleStatusList
					.add(new VehicleStatusImple(iteratorVechicleCases.next(), LocalDateTime.now(), Status.NOTARRIVED));
		}
	}

}
