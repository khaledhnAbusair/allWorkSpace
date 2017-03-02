package com.progressoft.jip.parsing.report;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Error")
public class Error {

	private String errorMsg;
	private List<String> records = new ArrayList<>();

	public Error(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void addRecord(String record) {
		records.add(record);
	}

}
