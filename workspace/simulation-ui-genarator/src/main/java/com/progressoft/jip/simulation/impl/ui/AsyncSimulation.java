package com.progressoft.jip.simulation.impl.ui;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;

public class AsyncSimulation implements Simulation {

	private Simulation simulation;
	
	

	public AsyncSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	@Override
	public void start(SimulationCase simCase) {

		simulation.start(simCase);
	}

	@Override
	public SimulationState getCurrentState() {
		return simulation.getCurrentState();
	}

	@Override
	public void addStateListener(SimulationStateListener listener) {
		
		simulation.addStateListener(new AsyncListener(listener));
	}

}
