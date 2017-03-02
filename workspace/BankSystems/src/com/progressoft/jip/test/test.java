package com.progressoft.jip.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		CurrentIBANValidator ibanValidator = new CurrentIBANValidator();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("IBANFormatTest.csv")));

		String line;
		long countTrue = 0;
		long countAll = 0;
		while ((line = bufferedReader.readLine()) != null) {
			if (ibanValidator.isValid(line))
				countTrue++;
			countAll++;
		}

		System.out.println(countAll == countTrue);

		bufferedReader.close();
	}
}
