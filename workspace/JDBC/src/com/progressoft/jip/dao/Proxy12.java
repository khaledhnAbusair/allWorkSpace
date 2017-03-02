package com.progressoft.jip.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy12 implements EmployeeDao {

	private InvocationHandler handler;

	@Override
	public void create(Employee employee) {
		Class<? extends Proxy12> type = this.getClass();
		try {
			Method method = type.getMethod("create", Employee.class);
			handler.invoke(this, method, new Object[] { employee });
		} catch (Throwable e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void update(Employee employee) {

	}

	@Override
	public void delete(Employee employee) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Iterable<Employee> listAll() {
		return null;
	}

}
