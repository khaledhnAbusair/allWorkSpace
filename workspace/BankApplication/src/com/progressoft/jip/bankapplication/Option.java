/**
 * 
 */
package com.progressoft.jip.bankapplication;

/**
 * @author u620
 *
 */
public abstract class Option {
	private int code;
	private String description;
	private static int count = 0;
	
	public Option(String description) {
		this.code = ++count;
		this.description = description;
	}
	
	public abstract void doOperation();
	
	@Override
	public String toString() {
		return String.format("%d- %s", code, description);
	}
	
	public int getCode() {
		return code;
	}
}
