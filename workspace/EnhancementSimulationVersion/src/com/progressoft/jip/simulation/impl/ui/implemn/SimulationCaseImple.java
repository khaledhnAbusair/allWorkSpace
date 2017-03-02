package com.progressoft.jip.simulation.impl.ui.implemn;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.simulation.impl.ui.swinggenerator.SwingCaseGenerator;

import java.util.List;

public class SimulationCaseImple implements SimulationCase {
	public int parkingLotsCount = 0;
	public List<VehicleCase> vehicleCases;
	public Map<String, Duration> taskDurations;

	public SimulationCaseImple(SwingCaseGenerator swingCaseGenerator) {
		taskDurations = new HashMap<>();
		parkingLotsCount = Integer.parseInt(swingCaseGenerator.parkingLotsCountField.getText());
		taskDurations.put(Type.TURCK.name() + "_" + Task.LOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.truckLoadDur.getText())));
		taskDurations.put(Type.TURCK.name() + "_" + Task.OFFLOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.truckOffLoadDur.getText())));
		taskDurations.put(Type.VAN.name() + "_" + Task.LOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.vanLoadDur.getText())));
		taskDurations.put(Type.VAN.name() + "_" + Task.OFFLOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.vanOffLoadDur.getText())));

		vehicleCases = new ArrayList<>(swingCaseGenerator.vehicleCases);
	}

	@Override
	public int parkingLotsCount() {
		return parkingLotsCount;
	}

	@Override
	public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
		return taskDurations.get(vehicleType.name() + "_" + vehicleTask.name());
	}

	@Override
	public Iterable<VehicleCase> vehicleCases() {
		return vehicleCases;
	}

}
