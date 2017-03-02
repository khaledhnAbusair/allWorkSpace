/**
 * 
 */
package com.progressoft.jip.paymentsproject.data;

import java.util.Iterator;

/**
 * @author u620
 *
 */
public interface DataReader<T> {
	public Iterator<T> iterator();
}
