package com.progressoft.jip.parsing.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Report")
public class Report {

	private Date startParsingTime;

	private Date endParsingTime;

	private String errorMessage;

	private String result;

	@XStreamImplicit(itemFieldName = "errors")
	private List<Error> errors = new ArrayList<>();

	public void setStartParsingTime(Date startParsingTime) {
		this.startParsingTime = startParsingTime;
	}

	public void setEndParsingTime(Date endParsingTime) {
		this.endParsingTime = endParsingTime;
	}

	public void addError(String message, String record) {
		boolean exist = false;
		for (Error error : errors) {
			if (error.getErrorMsg().equalsIgnoreCase(message)) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			errors.add(new Error(message));
		}
		for (Error error : errors) {
			if (error.getErrorMsg().equalsIgnoreCase(message)) {
				error.addRecord(record);
				break;
			}
		}
	}

	public void print() {

		if (errors.size() == 0)
			result = "Success";
		else {
			result = "Partially";
			errorMessage = "Some records are failed";
		}
		XStream xstream = new XStream();
		xstream.alias("Report", Report.class);
		xstream.alias("Error", Error.class);
		System.out.println(xstream.toXML(this));
	}

}
