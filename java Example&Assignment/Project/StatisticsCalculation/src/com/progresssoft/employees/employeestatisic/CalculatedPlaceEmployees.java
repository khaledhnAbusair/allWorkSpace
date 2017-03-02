package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculatedPlaceEmployees implements Operations<Employee> {
	private Map<String, Integer> mapPlace;

	public CalculatedPlaceEmployees() {
		mapPlace = new HashMap<String, Integer>();
	}

	@Override
	public void doOpertaions(Employee employee) {
		String birthPlace = employee.getBirthPlace();
		mapPlace.put(birthPlace, mapPlace.containsKey(birthPlace) ? mapPlace.get(birthPlace) + 1 : 1);

	}

	@Override
	public Map<String, Integer> getMap() {
		return mapPlace;
	}
}
