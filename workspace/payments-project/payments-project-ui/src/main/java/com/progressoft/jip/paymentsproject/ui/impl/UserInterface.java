package com.progressoft.jip.paymentsproject.ui.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.progressoft.jip.paymentsproject.ui.Displayer;
import com.progressoft.jip.paymentsproject.ui.Prompter;

public class UserInterface {

	private ArrayList<AbstractOption> options = new ArrayList<>();
	private InputStream inputStream;
	private OutputStream outputStream;
	private PrintStream printStream;
	private Scanner scanner;
	private Displayer displayer = field -> {
		printStream.format("%s%n", field);
	};

	public UserInterface(InputStream is, OutputStream os) {
		this.inputStream = is;
		this.outputStream = os;
		scanner = new Scanner(this.inputStream);
		printStream = new PrintStream(this.outputStream);
	}
	
	public void showOptionsAndPromptInput() {
		int option = 1;
		for (;;) {
			showOptions();
			option = promptInt();
			if (option > options.size() || option <= 0) {
				displayer.display("Wrong input");
			} else {
				options.get(option - 1).doOperation();
			}
		}
	}

	public void showOptions() {
		for (AbstractOption option : options) {
			displayer.display(option.toString());
		}
	}

	public void addOption(AbstractOption o) {
		options.add(o);
	}
	
	public <T> T cyclicPrompt(Prompter<T> prompter) {
		return new CyclicPrompter(displayer).prompt(prompter);
	}

	public String promptString() {
		Prompter<String> p = () -> {
			displayer.display("Waiting for input: ");
			return this.scanner.nextLine();
		};
		return cyclicPrompt(p);
	}

	public Integer promptInt() throws NumberFormatException {
		Prompter<Integer> p = () -> {
			displayer.display("Waiting for input (Integer only): ");
			Integer field;
			field = Integer.parseInt(this.scanner.nextLine());
			return field;
		};
		return cyclicPrompt(p);
	}
}