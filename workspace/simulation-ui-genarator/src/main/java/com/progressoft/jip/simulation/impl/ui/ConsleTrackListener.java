/**
 * 
 */
package com.progressoft.jip.simulation.impl.ui;

import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;

/**
 * @author khaled
 *
 */
public class ConsleTrackListener implements SimulationStateListener {

	@Override
	public void simulationStarted(SimulationState state) {
		System.out.println("Simulationis starte");
	}

	@Override
	public void statusChanged(SimulationState state) {
		System.out.println("Occupied Parking Lots" + state.occupiedParkinglots() + ", Is Full" + !state.hasEmptyLots());
	}

}
