package com.progresssoft.employees.employeestatisic;

import java.util.HashMap;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class CalculatedSalaryRange implements Operations<Employee> {
	private Map<String, Integer> mapSalary;

	public CalculatedSalaryRange() {
		mapSalary = new HashMap<String, Integer>();
		initializeMapSalaryVal();
	}

	@Override
	public void doOpertaions(Employee employee) {
		for (SalaryRange value : SalaryRange.values()) {
			value.salaryDoOperation(mapSalary, employee.getSalary());
		}
		
	}

	@Override
	public Map<String, Integer> getMap() {
		return mapSalary;
	}

	private void initializeMapSalaryVal() {
		mapSalary.put(SalaryRange.BELOW_350.name(), 0);
		mapSalary.put(SalaryRange.ABOVE_350_AND_BELOW_600.name(), 0);
		mapSalary.put(SalaryRange.ABOVE_600_AND_BELOW_1200.name(), 0);
		mapSalary.put(SalaryRange.ABOVE_1200.name(), 0);

	}

}
