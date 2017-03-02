/**
 * 
 */
package com.progressoft.jip.simulation.framework;

import java.time.Duration;

import com.progressoft.jip.simulation.framework.VehicleCase.Task;
import com.progressoft.jip.simulation.framework.VehicleCase.Type;

/**
 * @author phi01tech
 *
 */
public interface CaseGenerator {

	CaseGenerator setParkingLotsCount(int count);

	CaseGenerator addVehicleCase(VehicleCase vehicleCase);

	CaseGenerator setTaskDuration(VehicleCase.Type type, VehicleCase.Task task, Duration duration);

	default CaseGenerator setTruckLoadingDuration(Duration duration) {
		return setTaskDuration(Type.TURCK, Task.LOAD, duration);
	}

	default CaseGenerator setTruckOffLoadingDuration(Duration duration) {
		return setTaskDuration(Type.TURCK, Task.OFFLOAD, duration);
	}

	default CaseGenerator setVanOffLoadingDuration(Duration duration) {
		return setTaskDuration(Type.VAN, Task.OFFLOAD, duration);
	}

	default CaseGenerator setVanLoadingDuration(Duration duration) {
		return setTaskDuration(Type.VAN, Task.LOAD, duration);
	}

	SimulationCase generateCase();
}
