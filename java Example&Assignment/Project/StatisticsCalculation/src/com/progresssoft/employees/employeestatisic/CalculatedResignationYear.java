package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculatedResignationYear implements Operations<Employee> {
	private Map<Integer, Integer> mapResignationYear;

	public CalculatedResignationYear() {
		mapResignationYear = new HashMap<Integer, Integer>();
	}

	@Override
	public void doOpertaions(Employee employee) {
		int resignationYear = employee.getResignationDate();
		mapResignationYear.put(resignationYear,
				mapResignationYear.containsKey(resignationYear) ? mapResignationYear.get(resignationYear) + 1 : 1);
	}

	@Override
	public Map<Integer, Integer> getMap() {
		return mapResignationYear;
	}

}
