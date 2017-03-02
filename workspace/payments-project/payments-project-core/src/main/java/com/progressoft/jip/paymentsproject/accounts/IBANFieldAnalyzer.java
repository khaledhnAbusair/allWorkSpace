package com.progressoft.jip.paymentsproject.accounts;

import java.util.Iterator;

/**
 * 
 */

/**
 * @author u620
 *
 */
public interface IBANFieldAnalyzer {
	public Iterator<IBANStructurePattern> getPatterns();
}
