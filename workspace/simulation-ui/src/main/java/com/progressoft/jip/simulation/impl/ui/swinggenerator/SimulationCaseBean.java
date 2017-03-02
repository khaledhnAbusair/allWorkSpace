package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.progressoft.jip.simulation.SimulationCase;
import com.progressoft.jip.simulation.VehicleCase;
import com.progressoft.jip.simulation.VehicleCase.Task;
import com.progressoft.jip.simulation.VehicleCase.Type;



/**
 * @author u618
 *
 */
class SimulationCaseBean implements SimulationCase {

	private int count;
	private Collection<VehicleCase> cases;
	private Map<TypeTaskKey, Duration> durations;

	SimulationCaseBean(List<VehicleCase> vehicleCases,Map<TypeTaskKey, Duration> taskDurations, int parkingLotsCount) {
		this.cases = Collections.unmodifiableCollection(vehicleCases);
		this.count = parkingLotsCount;
		this.durations = Collections.unmodifiableMap(taskDurations);
	}

	@Override
	public Iterable<VehicleCase> vehicleCases() {
		return cases;
	}

	@Override
	public int parkingLotsCount() {
		return count;
	}

	@Override
	public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
		TypeTaskKey key = new TypeTaskKey(vehicleType,vehicleTask);
		return durations.get(key);
	}

}