package com.progressoft.jip.dao;

import java.time.LocalDate;

import com.progressoft.jip.dao.Employee.Gender;

/**
 * 
 */

/**
 * @author PSLPT 147
 *
 */
public class EmployeeDaoTest {
	private EmployeeDao employeeDao;

	public EmployeeDaoTest(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void test() {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();

		emp1.setEmpNo(1);
		emp1.setFirstName("Mohamamd");
		emp1.setLastName("Abdellatif");
		emp1.setGender(Gender.M);
		emp1.setBirthDate(LocalDate.now());
		emp1.setHireDate(LocalDate.now());

		emp2.setEmpNo(2);
		emp2.setFirstName("sami");
		emp2.setLastName("Hassan");
		emp2.setGender(Gender.M);
		emp2.setBirthDate(LocalDate.now());
		emp2.setHireDate(LocalDate.now());

		employeeDao.create(emp1);
		employeeDao.create(emp2);
	}

}
