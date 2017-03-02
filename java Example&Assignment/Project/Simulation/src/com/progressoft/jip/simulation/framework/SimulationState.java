/**
 * 
 */
package com.progressoft.jip.simulation.framework;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author phi01tech
 *
 */
public interface SimulationState {

	/**
	 * 
	 * @return the simulation related case
	 */
	SimulationCase getCase();

	/**
	 * 
	 * @return
	 */

	int occupiedParkinglots();

	/**
	 * 
	 * @return the start time of the simulation
	 */
	LocalDateTime startTime();

	/**
	 * 
	 * @return simulation completion time or <code>null</code> if it is still
	 *         running.
	 */
	LocalDateTime completeTime();

	boolean isRunning();

	Iterable<? extends VehicleStatus> getVehicleStatuses();

	default Stream<VehicleStatus> getVehicleStatusesAsStream() {
		Iterable<VehicleStatus> iterable = (Iterable<VehicleStatus>) getVehicleStatuses();
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	default boolean hasEmptyLots() {
		return occupiedParkinglots() < getCase().parkingLotsCount();

	}

}
