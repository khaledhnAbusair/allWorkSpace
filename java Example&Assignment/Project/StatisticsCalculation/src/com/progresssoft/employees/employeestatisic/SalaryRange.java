/**
 * 
 */
package com.progresssoft.employees.employeestatisic;

import java.util.Map;

import com.progresssoft.operations.SalaryOperation;

/**
 * @author u621
 *
 */
public enum SalaryRange implements SalaryOperation {

	BELOW_350 {
		@Override
		public void salaryDoOperation(Map<String, Integer> mapSalary, double salary) {
			if (salary <= 350) {
				Integer count = mapSalary.get(BELOW_350.name());
				mapSalary.put(BELOW_350.name(), count + 1);
			}
		}

	},
	ABOVE_350_AND_BELOW_600 {
		@Override
		public void salaryDoOperation(Map<String, Integer> mapSalary, double salary) {
			if (salary > 350 && salary <= 600) {
				Integer count = mapSalary.get(ABOVE_350_AND_BELOW_600.name());
				mapSalary.put(ABOVE_350_AND_BELOW_600.name(), count + 1);
			}
		}

	},
	ABOVE_600_AND_BELOW_1200 {

		@Override
		public void salaryDoOperation(Map<String, Integer> mapSalary, double salary) {
			if (salary > 600 && salary <= 1200) {
				Integer count = mapSalary.get(SalaryRange.ABOVE_600_AND_BELOW_1200.name());
				mapSalary.put(SalaryRange.ABOVE_600_AND_BELOW_1200.name(), count + 1);
			}

		}

	},
	ABOVE_1200 {
		@Override
		public void salaryDoOperation(Map<String, Integer> mapSalary, double salary) {
			if (salary > 1200) {
				Integer count = mapSalary.get(ABOVE_1200.name());
				mapSalary.put(ABOVE_1200.name(), count + 1);
			}

		}
	}
}
