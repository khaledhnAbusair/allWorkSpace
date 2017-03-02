package com.progressoft.jip.framework.impl;

import java.time.Duration;

import com.progressoft.jip.framework.VehicleCase;

public class VehicleCaseImplemntaion implements VehicleCase {

	private Type type;
	private Task task;
	private Duration duration;

	public VehicleCaseImplemntaion(Type type, Task task, Duration duration) {
		this.type = type;
		this.task = task;
		this.duration = duration;
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
		return duration;
	}

}
