package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import java.time.Duration;

import com.progressoft.jip.simulation.VehicleCase;

/**
 * @author u618
 *
 */
class VehicleCaseBean implements VehicleCase {

	private Type type;
	private Task task;
	private Duration arriveAfter;

	VehicleCaseBean(Type type, Task task, Duration arriveAfter) {
		
		this.type = type;
		this.task = task;
		this.arriveAfter = arriveAfter;
	}
	@Override
	public Type type() {
		return this.type;
	}

	@Override
	public Task task() {
		return this.task;
	}

	@Override
	public Duration arriveAfter() {
		return this.arriveAfter;
	}


}
