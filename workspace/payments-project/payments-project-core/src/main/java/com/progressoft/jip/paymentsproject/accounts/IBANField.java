/**
 * 
 */
package com.progressoft.jip.paymentsproject.accounts;

/**
 * @author u620
 *
 */
public interface IBANField {
	public String getIBAN();
	public String getCountryCode();
	public boolean isValid();
}