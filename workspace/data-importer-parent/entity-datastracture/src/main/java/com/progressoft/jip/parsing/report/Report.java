package com.progressoft.jip.parsing.report;

import static com.progressoft.jip.parsing.framework.Constant.FAILED;
import static com.progressoft.jip.parsing.framework.Constant.PARTIALLY;
import static com.progressoft.jip.parsing.framework.Constant.SUCCESS;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {

	private String startParsingTime;
	private String endParsingTime;
	private String errorMessage;
	private String result;

	private List<DataError> errors = new ArrayList<>();

	public Report() {
	}

	@XmlElementWrapper(name = "errors")
	@XmlElement(name = "data-error")
	public List<DataError> getErrors() {
		return errors;
	}

	public void setErrors(List<DataError> errors) {
		this.errors = errors;
	}

	@XmlElement
	public void setResult(String result) {
		this.result = result;
	}

	@XmlElement
	public void setEndTime(String endTime) {
		this.endParsingTime = endTime;
	}

	@XmlElement
	public void setStartTime(String startTime) {
		this.startParsingTime = startTime;
	}

	@XmlElement
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResult() {
		return result;
	}

	public String getStartTime() {
		return startParsingTime;
	}

	public String getEndTime() {
		return endParsingTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getSuccess() {
		return SUCCESS;
	}

	public String getPartially() {
		return PARTIALLY;
	}

	public String getFailed() {
		return FAILED;
	}

}
