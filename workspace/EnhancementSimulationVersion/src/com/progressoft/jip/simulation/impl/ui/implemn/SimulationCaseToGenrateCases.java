package com.progressoft.jip.simulation.impl.ui.implemn;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.simulation.impl.ui.swinggenerator.SwingCaseGenerator;

public class SimulationCaseToGenrateCases implements SimulationCase {

	private int count;
	Collection<VehicleCase> cases = new ArrayList<>();;
	Map<TypeTaskKey, Duration> durations = new HashMap<>();;

	public SimulationCaseToGenrateCases(SwingCaseGenerator swingCaseGenerator) {

		this.count = swingCaseGenerator.parkingLotsCount;
		this.cases = Collections.unmodifiableCollection(swingCaseGenerator.vehicleCases);
		this.durations = Collections.unmodifiableMap(swingCaseGenerator.taskDurations);

	}

	@Override
	public int parkingLotsCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
		TypeTaskKey key = new TypeTaskKey();
		key.task = vehicleTask;
		key.type = vehicleType;
		return durations.get(key);
	}

	@Override
	public Iterable<VehicleCase> vehicleCases() {
		// TODO Auto-generated method stub
		return cases;
	}

}
