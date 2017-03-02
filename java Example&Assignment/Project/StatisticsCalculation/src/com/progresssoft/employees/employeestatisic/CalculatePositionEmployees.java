package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculatePositionEmployees implements Operations<Employee> {
	private Map<String, Integer> mapPositionEmployee;

	public CalculatePositionEmployees() {
		mapPositionEmployee = new HashMap<String, Integer>();
	}

	@Override
	public void doOpertaions(Employee employee) {
		String position = employee.getPosition();
		mapPositionEmployee.put(position,
				mapPositionEmployee.containsKey(position) ? mapPositionEmployee.get(position) + 1 : 1);
	}

	@Override
	public Map<String, Integer> getMap() {
		return mapPositionEmployee;
	}

}
