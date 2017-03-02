/**
 * 
 */
package com.progressoft.simulation.console;

import java.time.Duration;

import com.progressoft.jip.framework.CaseGenerator;
import com.progressoft.jip.framework.Simulation;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.framework.impl.CaseGeneratorImple;
import com.progressoft.jip.framework.impl.VehicleCaseImplemntaion;
import com.progressoft.jip.simulation.implemntation.SimualtionImple;

/**
 * @author khaled
 *
 */
public class SimulationConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CaseGenerator caseGenerator = new CaseGeneratorImple();
		caseGenerator.setParkingLotsCount(1);
		caseGenerator.setTruckLoadingDuration(Duration.ofSeconds(10));
		caseGenerator.setTruckOffLoadingDuration(Duration.ofSeconds(10));
		caseGenerator.setVanLoadingDuration(Duration.ofSeconds(8));
		caseGenerator.setVanOffLoadingDuration(Duration.ofSeconds(8));

		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.LOAD, Duration.ofSeconds(10)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.OFFLOAD, Duration.ofSeconds(10)));
		caseGenerator.addVehicleCase(new VehicleCaseImplemntaion(Type.TURCK, Task.LOAD, Duration.ofSeconds(10)));

		Simulation simulation = new SimualtionImple();
		
		
		simulation.start(caseGenerator.generateCase());
	}

}
