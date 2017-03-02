package com.progressoft.jip.paymentsproject.ui.impl;

import com.progressoft.jip.paymentsproject.ui.Displayer;
import com.progressoft.jip.paymentsproject.ui.Prompter;

//import com.progressoft.jip.paymentsproject.ui.impl.UserInterface.Prompter;

public class CyclicPrompter {
	
	private Displayer displayer;
	
	public CyclicPrompter(Displayer displayer) {
		this.displayer = displayer;
	}
	
	public <T> T prompt(Prompter<T> prompter) {
		boolean validInput = false;
		T field = null;
		do {
			try {
				field = prompter.prompt();
			} catch (RuntimeException e) {
				displayer.display("Wrong input format \"" + e.getClass().getSimpleName() + "\". Try again");
				validInput = false;
			}
		} while (!validInput);
		return field;
	}
}
