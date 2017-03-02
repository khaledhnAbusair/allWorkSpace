package com.progressoft.jip.simulation;

import java.time.Duration;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.progressoft.jip.simulation.VehicleCase.Task;
import com.progressoft.jip.simulation.VehicleCase.Type;

/**
 * Represents a simulation case to execute.
 * 
 * @author phi01tech
 *
 */
public interface SimulationCase {

	/**
	 * Returns the number of parking lots for this case.
	 * 
	 * @return
	 */
	int parkingLotsCount();
	
	/**
	 * Returns the duration (time needed) for a vehicle of type
	 * <code>vehicleType</code> to perform <code>vehicleTask</code>.
	 * 
	 * @param vehicleType
	 *            the type of vehicle.
	 * @param vehicleTask
	 *            the task of vehicle.
	 * @return the duration needed
	 */
	Duration getTaskDuration(Type vehicleType, Task vehicleTask);

	/**
	 * A list for vehicle cases.
	 * 
	 * @return
	 */
	Iterable<VehicleCase> vehicleCases();
	
	/**
	 * A stream for vehicle cases.
	 * 
	 * @return
	 */
	default Stream<VehicleCase> vehicleCasesAsStream() {
		Iterable<VehicleCase> cases = vehicleCases();
		return StreamSupport.stream(cases.spliterator(), false);
	}

	/**
	 * The 
	 * @return
	 */
	default Duration getTruckLoadDuration() {
		return getTaskDuration(Type.TURCK, Task.LOAD);
	}

	default Duration getVanLoadDuration() {
		return getTaskDuration(Type.VAN, Task.LOAD);
	}

	default Duration getTruckOffloadDuration() {
		return getTaskDuration(Type.VAN, Task.OFFLOAD);
	}

	default Duration getVanOffloadDuration() {
		return getTaskDuration(Type.VAN, Task.OFFLOAD);
	}
}
