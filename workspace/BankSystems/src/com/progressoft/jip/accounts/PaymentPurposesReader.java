package com.progressoft.jip.accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentPurposesReader {
	private static final String FILE_NAME = "PaymentPurposes.csv";
	private List<String> paymentNames = new ArrayList<>();
	private List<String> paymentCodes = new ArrayList<>();

	public PaymentPurposesReader() {
		readFromFile();
	}

	private void readFromFile() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
			readLines(bufferedReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readLines(BufferedReader bufferedReader) throws IOException {
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] info = line.split(",");
			paymentNames.add(info[0]);
			paymentCodes.add(info[1]);
		}
	}
	
	public boolean lookupName(String name) {
		return paymentNames.indexOf(name) != -1;
	}
	
	public String getCodeFromName(String name) {
		return paymentCodes.get(paymentNames.indexOf(name));
	}
}
