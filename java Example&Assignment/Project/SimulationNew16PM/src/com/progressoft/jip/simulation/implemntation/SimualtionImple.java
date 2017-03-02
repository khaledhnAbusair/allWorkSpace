package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		Iterator<VehicleCase> iteratorVechicleCases = simCase.vehicleCases().iterator();
		while (iteratorVechicleCases.hasNext()) {
			vehicleStatusList
					.add(new VehicleStatusImple(iteratorVechicleCases.next(), LocalDateTime.now(), Status.NOTARRIVED));
		}
		simState = new SimulationStateImple(simCase, startSimualtionTime, vehicleStatusList);
		parkedCar = new ParkedCar(vehicleStatusList, startSimualtionTime, simState);
		new Thread(parkedCar).start();
	}

	@Override
	public SimulationState getCurrentState() {

		return new SimulationStateImple(simCase, startSimualtionTime, vehicleStatusList);

	}

	@Override
	public void addStateListener(SimulationStateListener listener) {
		parkedCar.setListner(listener);
		this.listener = listener;

	}

}
