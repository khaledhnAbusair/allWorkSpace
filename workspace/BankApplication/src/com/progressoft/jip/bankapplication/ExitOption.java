/**
 * 
 */
package com.progressoft.jip.bankapplication;

/**
 * @author u620
 *
 */
public class ExitOption extends Option{
	
	private static final int EXIT_CODE = 0;
	
	public ExitOption(String description) {
		super(description);
	}

	@Override
	public void doOperation() {
		UI.show("Thank you for using our bank application");
		System.exit(EXIT_CODE);
	}
	
}
