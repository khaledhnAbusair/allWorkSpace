/**
 * 
 */
package com.progressoft.jip.simulation.framework;

/**
 * @author phi01tech
 *
 */
public interface Simulation {

	public void start(SimulationCase simCase);

	public SimulationState getCurrentState();

	public void addStateListener(SimulationStateListener listener);

}
