package net.webservicex;

public class TestCallingWebService {
	public static void main(String[] args) {
		CurrencyConvertor convertor = new CurrencyConvertor();
		CurrencyConvertorSoap convertorSoap = convertor.getCurrencyConvertorSoap();
		double conversionRate = convertorSoap.conversionRate(Currency.JOD, Currency.BRL);
		System.out.println(conversionRate);
	}
}
