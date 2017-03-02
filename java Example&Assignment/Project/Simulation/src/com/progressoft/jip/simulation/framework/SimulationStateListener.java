/**
 * 
 */
package com.progressoft.jip.simulation.framework;

/**
 * @author phi01tech
 *
 */
public interface SimulationStateListener {

	public void simulationStarted(SimulationState state);

	public void statusChanged(SimulationState state);
}
