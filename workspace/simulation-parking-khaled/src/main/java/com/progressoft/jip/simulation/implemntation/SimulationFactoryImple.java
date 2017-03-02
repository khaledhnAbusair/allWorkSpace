package com.progressoft.jip.simulation.implemntation;

import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.SimulationFactory;

public class SimulationFactoryImple implements SimulationFactory {

	@Override
	public Simulation createSimulation() {
		return new SimualtionImple();
	}
	

	
}
