/**
 * 
 */
package com.progressoft.jip.social.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author PSLPT 147
 *
 */
@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostMessage {

	@XmlElement(name = "user-email")
	private String email;

	@XmlElement(name = "content")
	private String content;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
