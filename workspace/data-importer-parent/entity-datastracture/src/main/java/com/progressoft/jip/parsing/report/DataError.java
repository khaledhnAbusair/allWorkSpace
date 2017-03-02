package com.progressoft.jip.parsing.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data-error")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataError {
	
	@XmlElement(name = "error-message")
	private String errorMessage;
	@XmlElement(name = "data-record")
	private String dataRecord;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDataRecord() {
		return dataRecord;
	}

	public void setDataRecord(String dataRecord) {
		this.dataRecord = dataRecord;
	}

}
