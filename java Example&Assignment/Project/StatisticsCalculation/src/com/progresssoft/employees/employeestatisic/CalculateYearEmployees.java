package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculateYearEmployees implements Operations<Employee> {

	private Map<Integer, Integer> mapyear;

	public CalculateYearEmployees() {
		mapyear = new HashMap<Integer, Integer>();
	}

	@Override
	public void doOpertaions(Employee employee) {
		int empYear = employee.getBirthYear();
		mapyear.put(empYear, mapyear.containsKey(empYear) ? mapyear.get(empYear) + 1 : 1);
	}

	@Override
	public Map<Integer, Integer> getMap() {
		return mapyear;
	}
}
