package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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
class SimulationCaseImpl implements SimulationCase {

	int parkingLotsCount = 0;
	Map<String, Duration> durations = new HashMap<>();
	List<VehicleCase> cases;
	

	SimulationCaseImpl(SwingCaseGenerator swingCaseGenerator) {
		parkingLotsCount = Integer.parseInt(swingCaseGenerator.parkingLotsCountField.getText());
		durations.put(Type.TURCK.name() + "_" + Task.LOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.truckLoadDur.getText())));
		durations.put(Type.TURCK.name() + "_" + Task.OFFLOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.truckOffLoadDur.getText())));
		durations.put(Type.VAN.name() + "_" + Task.LOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.vanLoadDur.getText())));
		durations.put(Type.VAN.name() + "_" + Task.OFFLOAD.name(),
				Duration.ofSeconds(Integer.parseInt(swingCaseGenerator.vanOffLoadDur.getText())));

		cases = new ArrayList<>(swingCaseGenerator.vehicleCases);
	}

	@Override
	public int parkingLotsCount() {
		return parkingLotsCount;
	}

	@Override
	public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {
		return durations.get(vehicleType.name() + "_" + vehicleTask.name());
	}

	@Override
	public Iterable<VehicleCase> vehicleCases() {
		return cases;
	}


}
