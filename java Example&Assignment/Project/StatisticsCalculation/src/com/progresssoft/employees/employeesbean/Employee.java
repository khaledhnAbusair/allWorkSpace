package com.progresssoft.employees.employeesbean;

import java.time.LocalDateTime;

/**
 * Created by u621 on 15/10/2016.
 */
public class Employee {

	private LocalDateTime resignationDate;
	private LocalDateTime hiringYear;
	private LocalDateTime birthYear;
	private String birthPlace;
	private String firstName;
	private String lastName;
	private String position;
	private double salary;

	public Employee(String firstName, String lastName, String birthPlace, String position, double salary,
			LocalDateTime birthYear, LocalDateTime resignationDate, LocalDateTime hiringYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthPlace = birthPlace;
		this.position = position;
		this.salary = salary;
		this.birthYear = birthYear;
		this.resignationDate = resignationDate;
		this.hiringYear = hiringYear;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getBirthYear() {
		return birthYear.getYear();
	}

	public void setBirthYear(LocalDateTime birthYear) {
		this.birthYear = birthYear;
	}

	public int getResignationDate() {
		return resignationDate.getYear();
	}

	public void setResignationDate(LocalDateTime resignationDate) {
		this.resignationDate = resignationDate;
	}

	public int getHiringYear() {
		return hiringYear.getYear();
	}

	public void setHiringYear(LocalDateTime hiringYear) {
		this.hiringYear = hiringYear;
	}

	@Override
	public String toString() {
		return "Employee{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", birthPlace='"
				+ birthPlace + '\'' + ", position='" + position + '\'' + ", salary=" + salary + ", birthYear="
				+ birthYear + ", resignationDate=" + resignationDate + ", hiringYear=" + hiringYear + '}';
	}
}
