package webservices.calculater.impl;

import javax.jws.WebService;

import webservices.calculater.Calculator;

@WebService(endpointInterface = "webservices.calculater.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public int add(int left, int right) {
		return left + right;
	}

	@Override
	public int subtract(int a, int b) {
		return a - b;
	}

}
