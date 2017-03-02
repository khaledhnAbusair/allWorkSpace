/**
 * 
 */
package com.progressoft.jip.paymentsproject.ui;

/**
 * @author u620
 *
 */
@FunctionalInterface
public interface Prompter<T> {
		public T prompt();
}
