/**
 * 
 */
package com.progressoft.jip.paymentsproject.ui.impl;

import com.progressoft.jip.paymentsproject.ui.Displayer;

/**
 * @author u620
 *
 */
public class ExitOption extends AbstractOption {

	private static final int EXIT_CODE = 0;
	private Displayer displayer;

	public ExitOption(String description, Displayer displayer) {
		super(description);
		this.displayer = displayer;
	}

	@Override
	public void doOperation() {
		displayer.display("Thank you for using our bank application");
		System.exit(EXIT_CODE);
	}

}
