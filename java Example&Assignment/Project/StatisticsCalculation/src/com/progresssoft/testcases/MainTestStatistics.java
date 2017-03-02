package com.progresssoft.testcases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.progresssoft.employees.employeesbean.Employee;
import com.progresssoft.employees.employeestatisic.CalculateHiringYear;
import com.progresssoft.employees.employeestatisic.CalculatePositionEmployees;
import com.progresssoft.employees.employeestatisic.CalculateYearEmployees;
import com.progresssoft.employees.employeestatisic.CalculatedPlaceEmployees;
import com.progresssoft.employees.employeestatisic.CalculatedResignationYear;
import com.progresssoft.employees.employeestatisic.CalculatedSalaryRange;
import com.progresssoft.operations.Operations;
import com.progresssoft.statistic.GenricStatistic;
import com.progresssoft.statistic.GenricStatisticCollector;
import com.progresssoft.stringstatistices.LowerCaseCharacters;
import com.progresssoft.stringstatistices.NonWordCharacters;
import com.progresssoft.stringstatistices.TotalNumberOfNumbersfound;
import com.progresssoft.stringstatistices.UpperCaseCharacters;
import com.progresssoft.stringstatistices.WordCharacters;

/**
 * Created by u621 on 15/10/2016.
 */
/**
 * @author u621
 *
 */
public class MainTestStatistics {
	private static final TotalNumberOfNumbersfound numbersfoundObject = new TotalNumberOfNumbersfound();
	private static final UpperCaseCharacters upperCaseCharactersObject = new UpperCaseCharacters();
	private static final LowerCaseCharacters lowerCaseCharactersObject = new LowerCaseCharacters();
	private static final NonWordCharacters nonWordCharactersObject = new NonWordCharacters();
	private static final WordCharacters wordCharactersObject = new WordCharacters();

	private static final CalculatePositionEmployees positionEmployeesObject = new CalculatePositionEmployees();
	private static final CalculatedResignationYear resignationYearObject = new CalculatedResignationYear();
	private static final CalculatedPlaceEmployees placeEmployeesObject = new CalculatedPlaceEmployees();
	private static final CalculateYearEmployees yearEmployeesObject = new CalculateYearEmployees();
	private static final CalculatedSalaryRange salaryRangeObject = new CalculatedSalaryRange();
	private static final CalculateHiringYear hiringYearObject = new CalculateHiringYear();

	private static Map<String, Operations<Employee>> operationEmployeeMap = new HashMap<>();
	private static Map<String, Operations<String>> opertaionStringMap = new HashMap<>();

	public static void main(String[] args) {

		populateEmployeeOpertaionsMap();
		populateStringOpertaionsMap();

		String input = "Khaled WALEED@#$ 14850 ";
		passedStringValues(input);

		Employee employee1 = emplaoyee1(LocalDateTime.of(2016, 10, 12, 12, 25), LocalDateTime.of(2015, 10, 12, 12, 4),
				LocalDateTime.of(2017, 11, 8, 10, 25));
		Employee employee2 = employee2(LocalDateTime.of(2016, 10, 12, 12, 25), LocalDateTime.of(2017, 11, 8, 10, 25));
		Employee employee3 = employee3(LocalDateTime.of(2016, 10, 12, 12, 25), LocalDateTime.of(2015, 10, 12, 12, 4),
				LocalDateTime.of(2016, 12, 6, 11, 23));
		Employee employee4 = employee4(LocalDateTime.of(2015, 10, 12, 12, 4), LocalDateTime.of(2016, 12, 6, 11, 23),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		Employee employee5 = employee5(LocalDateTime.of(2016, 10, 12, 12, 25), LocalDateTime.of(2015, 10, 12, 12, 4),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		Employee employee6 = employee6(LocalDateTime.of(2016, 10, 12, 12, 25), LocalDateTime.of(2015, 10, 12, 12, 4),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		Employee employee7 = employee7(LocalDateTime.of(2017, 11, 8, 10, 25), LocalDateTime.of(2015, 11, 8, 10, 17),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		Employee employee8 = employee8(LocalDateTime.of(2016, 12, 6, 11, 23), LocalDateTime.of(2015, 11, 8, 10, 17),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		Employee employee9 = employee9(LocalDateTime.of(2016, 12, 6, 11, 23), LocalDateTime.of(2015, 11, 8, 10, 17),
				LocalDateTime.of(2015, 11, 8, 10, 17));
		passedEmployees(employee1, employee2, employee3, employee4, employee5, employee6, employee7, employee8,
				employee9);

	}

	public static void tableFormat() {
		System.out.printf("%2s", "-");
		for (int i = 0; i < 160; i++) {

			System.out.printf("%s", "-");
		}
	}

	private static void populateStringOpertaionsMap() {

		opertaionStringMap.put("Number Of Num Char", numbersfoundObject);
		opertaionStringMap.put("Number Of UpperCase", upperCaseCharactersObject);
		opertaionStringMap.put("Number Of LowerCase", lowerCaseCharactersObject);
		opertaionStringMap.put("Number Of WordChar", wordCharactersObject);
		opertaionStringMap.put("Number Of Non Word", nonWordCharactersObject);

	}

	private static void populateEmployeeOpertaionsMap() {
		operationEmployeeMap.put("Employee Postion", positionEmployeesObject);
		operationEmployeeMap.put("Resignation Year", resignationYearObject);
		operationEmployeeMap.put("Birth place", placeEmployeesObject);
		operationEmployeeMap.put("Salary ranges", salaryRangeObject);
		operationEmployeeMap.put("Hiring year", hiringYearObject);
		operationEmployeeMap.put("Birth year", yearEmployeesObject);
	}

	private static void passedEmployees(Employee employee1, Employee employee2, Employee employee3, Employee employee4,
			Employee employee5, Employee employee6, Employee employee7, Employee employee8, Employee employee9) {
		ArrayList<Employee> employeeValueAsList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3,
				employee4, employee5, employee6, employee7, employee8, employee9));

		GenricStatisticCollector<Employee> collector = iterateEmployees(employeeValueAsList);
		System.out.printf("%n%n%35s %22s %n", " Statistic Name ", "Value" + "\n");
		iterateVisitor(collector);
	}

	private static GenricStatisticCollector<Employee> iterateEmployees(ArrayList<Employee> employees) {
		GenricStatisticCollector<Employee> collector = new GenricStatisticCollector<>(operationEmployeeMap);

		for (Employee employee : employees) {
			collector.visit(employee);
		}
		return collector;
	}

	private static void passedStringValues(String input) {
		ArrayList<String> stringValueAsList = new ArrayList<>(Arrays.asList(input));
		GenricStatisticCollector<String> collector = iterateString(stringValueAsList);
		System.out.printf("%n%n%35s %22s %n", " Statistic Name ", "Value" + "\n");

		iterateVisitor(collector);
	}

	private static GenricStatisticCollector<String> iterateString(ArrayList<String> inputValue) {
		GenricStatisticCollector<String> collector = new GenricStatisticCollector<>(opertaionStringMap);

		for (String values : inputValue) {
			collector.visit(values);
		}
		return collector;
	}

	private static void iterateVisitor(GenricStatisticCollector<?> collector) {
		Iterator<GenricStatistic> calculatedStatistics = collector.calculatedStatistics();
		tableFormat();
		while (calculatedStatistics.hasNext()) {
			GenricStatistic genricStatistic = calculatedStatistics.next();
			System.out.printf("%n%1$-20s , %2$-30s,%3$s,%n", genricStatistic.getKey(), genricStatistic.getDetails(),
					genricStatistic.getValue());
		}
		tableFormat();
	}

	private static Employee employee9(LocalDateTime of, LocalDateTime of2, LocalDateTime of3) {
		Employee employee9 = new Employee("Samer", "Mohammed", "Palestine", "Java Web Developer", 10.1, of, of2, of3);
		return employee9;
	}

	private static Employee employee8(final LocalDateTime date4, final LocalDateTime date5, final LocalDateTime date6) {
		Employee employee8 = new Employee("fadi", "rami", "Aqaba", "Analysis", 1250, date4, date6, date5);
		return employee8;
	}

	private static Employee employee7(final LocalDateTime date3, final LocalDateTime date5, final LocalDateTime date7) {
		Employee employee7 = new Employee("Mohammed", "waleed", "Irbid", "Analysis", 470, date5, date3, date7);
		return employee7;
	}

	private static Employee employee6(final LocalDateTime date1, final LocalDateTime date2, final LocalDateTime date6) {
		Employee employee6 = new Employee("Ali", "Abusair", "Tafila", "Project Manager", 804.12, date1, date2, date6);
		return employee6;
	}

	private static Employee employee5(final LocalDateTime date1, final LocalDateTime date2, final LocalDateTime date7) {
		Employee employee5 = new Employee("Khaled", "sameer", "Aqaba", "Project Manager", 541, date1, date7, date2);
		return employee5;
	}

	private static Employee employee4(final LocalDateTime date2, final LocalDateTime date4, final LocalDateTime date5) {
		Employee employee4 = new Employee("fadi", "rami", "Amman", "C# Developer", 620, date2, date4, date5);
		return employee4;
	}

	private static Employee employee3(final LocalDateTime date1, final LocalDateTime date2, final LocalDateTime date4) {
		Employee employee3 = new Employee("Mohammed", "waleed", "Irbid", "Android Developer", 1, date2, date1, date4);
		return employee3;
	}

	private static Employee employee2(final LocalDateTime date1, final LocalDateTime date3) {
		Employee employee2 = new Employee("Ali", "Abusair", "Zarqa", ".Net Developer", 1200, date1, date1, date3);
		return employee2;
	}

	private static Employee emplaoyee1(final LocalDateTime date1, final LocalDateTime date2,
			final LocalDateTime date3) {
		Employee employee1 = new Employee("Khaled", "sameer", "Amman", "Java Developer", 580, date1, date2, date3);
		return employee1;
	}

}
