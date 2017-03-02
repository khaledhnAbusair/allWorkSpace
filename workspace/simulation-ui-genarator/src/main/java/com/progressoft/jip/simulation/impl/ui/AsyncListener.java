package com.progressoft.jip.simulation.impl.ui;

import com.progressoft.jip.framework.SimulationState;
import com.progressoft.jip.framework.SimulationStateListener;

public class AsyncListener implements SimulationStateListener {

	private SimulationStateListener listener;

	public AsyncListener(SimulationStateListener listener) {
		this.listener = listener;
	}

	@Override
	public void simulationStarted(SimulationState state) {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
				listener.simulationStarted(state);
			}
		}.start();

	}

	@Override
	public void statusChanged(SimulationState state) {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
				listener.simulationStarted(state);
			}
		}.start();

	}

}
