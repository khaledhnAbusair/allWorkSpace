package com.progressoft.jip.simulation.test;

import java.time.Duration;

import com.progressoft.jip.framework.CaseGenerator;
import com.progressoft.jip.framework.SimulationCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.framework.impl.CaseGeneratorImple;
import com.progressoft.jip.framework.impl.VehicleCaseImplemntaion;

public class CaseGenratorTest {

	public static void main(String[] args) {

		// Same StringBuilder
		// StringBuilder builder=new StringBuilder();
		// builder.append("A").append("B");//same of the set in caseGenrator
		// builder.toString(); // genrateMethod for StringBuilder

		// Example of (Builder Pattern)
		CaseGenerator caseGenerator = new CaseGeneratorImple();

		caseGenerator.setParkingLotsCount(2).setTruckLoadingDuration(Duration.ofSeconds(10))
				.setTruckOffLoadingDuration(Duration.ofSeconds(8)).setTruckLoadingDuration(Duration.ofSeconds(7));
		// caseGenerator is the same call to set because every method return
		// type of CaseGenerator

		// -----------------------------
		// add vehicle cases
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.OFFLOAD, Duration.ofSeconds(10)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.LOAD, Duration.ofSeconds(12)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.VAN, Task.OFFLOAD, Duration.ofSeconds(8)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.VAN, Task.LOAD, Duration.ofSeconds(9)));

		SimulationCase simulationCase = caseGenerator.generateCase();

		caseGenerator.setParkingLotsCount(3);

		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.OFFLOAD, Duration.ofSeconds(8)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.LOAD, Duration.ofSeconds(5)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.VAN, Task.OFFLOAD, Duration.ofSeconds(6)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.VAN, Task.LOAD, Duration.ofSeconds(7)));

		SimulationCase simulationCase2 = caseGenerator.generateCase();

		System.out.println("SimCase1 Parking Count : " + simulationCase.parkingLotsCount());
		System.out.println("SimCase2 Parking Count: " + simulationCase2.parkingLotsCount());
		System.out.println("SimCase1 Vehicle Count:" + simulationCase.vehicleCasesAsStream().count());
		System.out.println("SimCase2 Vehicle Count :" + simulationCase2.vehicleCasesAsStream().count());

	}
}
