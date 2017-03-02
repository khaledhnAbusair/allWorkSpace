package com.progressoft.jip.framework.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.progressoft.jip.framework.CaseGenerator;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;

public class CaseGeneratorImple implements CaseGenerator {

	private int parkingLotsCount;
	private ArrayList<VehicleCase> cases = new ArrayList<>();
	private Map<Pair<Type, Task>, Duration> durationsMap = new HashMap<>();

	@Override
	public CaseGenerator setParkingLotsCount(int count) {
		this.parkingLotsCount = count;
		return this;
	}

	@Override
	public CaseGenerator addVehicleCase(VehicleCase vehicleCase) {

		cases.add(vehicleCase);
		return this;
	}

	@Override
	public CaseGenerator setTaskDuration(Type type, Task task, Duration duration) {

		Pair<Type, Task> pair = Pair.of(type, task);
		durationsMap.put(pair, duration);
		return this;
	}

	@Override
	public SimulationCase generateCase() {
		// Construct simulation case according to setting
		SimulationCaseImplemntaion caseImplemntaion = new SimulationCaseImplemntaion();
		caseImplemntaion.parkingLotsCount = parkingLotsCount;
		// Decorator around cases not copy of list And Map
		// caseImplemntaion.durations =
		// Collections.unmodifiableMap(durationsMap);
		// caseImplemntaion.cases = Collections.unmodifiableList(cases);
		caseImplemntaion.durations = new HashMap<>(durationsMap);
		caseImplemntaion.cases = new ArrayList<>(cases);

		return caseImplemntaion;
		// مشان كل نسخة تطلع ماالها نفس خصائص النسخة الأولي
	}

	private class SimulationCaseImplemntaion implements SimulationCase {

		int parkingLotsCount;
		Map<Pair<Type, Task>, Duration> durations;
		Iterable<VehicleCase> cases;

		@Override
		public int parkingLotsCount() {
			return parkingLotsCount;
		}

		@Override
		public Duration getTaskDuration(Type vehicleType, Task vehicleTask) {

			Pair<Type, Task> key = Pair.of(vehicleType, vehicleTask);
			return durations.get(key);
		}

		@Override
		public Iterable<VehicleCase> vehicleCases() {
			return cases;
		}

	}

}
