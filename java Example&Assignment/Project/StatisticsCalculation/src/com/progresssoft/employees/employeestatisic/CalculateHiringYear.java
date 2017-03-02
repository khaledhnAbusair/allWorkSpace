package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculateHiringYear implements Operations<Employee> {
	private Map<Integer, Integer> mapHiringYear;

	public CalculateHiringYear() {
		mapHiringYear = new HashMap<Integer, Integer>();
	}

	@Override
	public void doOpertaions(Employee employee) {
		int hiringYear = employee.getHiringYear();
		mapHiringYear.put(hiringYear, mapHiringYear.containsKey(hiringYear) ? mapHiringYear.get(hiringYear) + 1 : 1);
	}

	@Override
	public Map<Integer, Integer> getMap() {
		return mapHiringYear;
	}
}
