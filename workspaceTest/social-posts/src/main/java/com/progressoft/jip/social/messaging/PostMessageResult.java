package com.progressoft.jip.social.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post-result")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostMessageResult {

	public static enum Status {
		SUCCESS,
		FAILED;
	}

	@XmlElement(name = "status", nillable = false)
	private Status status;

	@XmlElement(name = "details")
	private String details;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
