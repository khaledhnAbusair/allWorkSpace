package com.progressoft.jip.simulation.implemntation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.progressoft.jip.simulation.framework.VehicleCase;
import com.progressoft.jip.simulation.framework.VehicleStatus;

public class VehicleStatusImple implements VehicleStatus {

	private Map<Status, LocalDateTime> statusMap = new HashMap<>();
	private LocalDateTime arrivalTime;
	private VehicleCase vehicleCase;
	private Status status;

	public VehicleStatusImple(VehicleCase vehicleCase, LocalDateTime arrivalTime, Status status) {
		this.vehicleCase = vehicleCase;
		this.arrivalTime = arrivalTime;
		this.status = status;
		initializeStatus();
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setStatus(Status status) {
		this.status = status;
		statusMap.put(status, LocalDateTime.now());
	}

	@Override
	public VehicleCase vehicle() {
		return vehicleCase;
	}

	@Override
	public LocalDateTime arrivalTime() {
		return arrivalTime;
	}

	@Override
	public LocalDateTime statusTime(Status status) {
		return statusMap.get(status);
	}

	@Override
	public Status status() {
		return status;
	}

	private void initializeStatus() {
		statusMap.put(Status.WAITING, null);
		statusMap.put(Status.PARKED, null);
		statusMap.put(Status.LEFT, null);
		statusMap.put(Status.NOTARRIVED, null);
	}

}
