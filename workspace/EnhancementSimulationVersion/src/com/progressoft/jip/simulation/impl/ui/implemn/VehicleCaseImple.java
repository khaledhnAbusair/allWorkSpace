package com.progressoft.jip.simulation.impl.ui.implemn;

import java.time.Duration;

import com.progressoft.jip.framework.VehicleCase;

public class VehicleCaseImple implements VehicleCase {
	private Type type;
	private Task task;
	private Duration arriveAfter;

	public VehicleCaseImple(Type type, Task task, Duration arriveAfter) {
		this.type = type;
		this.task = task;
		this.arriveAfter = arriveAfter;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public Task task() {
		return task;
	}

	@Override
	public Duration arriveAfter() {
		return arriveAfter;
	}

}
