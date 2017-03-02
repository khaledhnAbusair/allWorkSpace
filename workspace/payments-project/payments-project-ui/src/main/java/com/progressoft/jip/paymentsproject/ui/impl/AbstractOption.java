/**
 * 
 */
package com.progressoft.jip.paymentsproject.ui.impl;

import com.progressoft.jip.paymentsproject.ui.Option;

/**
 * @author u620
 *
 */
public abstract class AbstractOption implements Option {
	private static int count = 0;
	private int code;
	private String description;
	
	public AbstractOption(String description) {
		this.code = ++count;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("%d- %s", code, description);
	}
	
	public int getCode() {
		return code;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
