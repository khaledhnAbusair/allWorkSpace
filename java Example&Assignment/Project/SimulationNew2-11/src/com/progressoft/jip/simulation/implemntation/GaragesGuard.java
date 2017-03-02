package com.progressoft.jip.simulation.implemntation;

public class GaragesGuard {

	private static GaragesGuard garagesGuard = new GaragesGuard();

	private GaragesGuard() {
	}

	public static GaragesGuard getGuardGarages() {
		return garagesGuard;
	}

}
