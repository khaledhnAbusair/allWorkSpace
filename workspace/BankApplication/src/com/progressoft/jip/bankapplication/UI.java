package com.progressoft.jip.bankapplication;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

	private static ArrayList<Option> options;
	private static InputStream inputStream = System.in;
	private static OutputStream outputStream = System.out;
	private static PrintStream printStream = new PrintStream(outputStream);
	private static Scanner scanner = new Scanner(inputStream);
	private static ExitOption exitOption;

	public UI() {

	}

	public static void interact() {
		buildOptions();
		showOptions();
		iterateShowMenu();
	}

	public static void iterateShowMenu() {
		int option = 1;
		options = new ArrayList<>();
		option = promptInt();

		while (option != exitOption.getCode()) {
			if (option > exitOption.getCode() || option <= 0)
				show("Wrong input");
			options.get(option - 1).doOperation();
			showOptions();
			option = promptInt();
		}
	}

	private static void showOptions() {
		for (Option option : options) {
			show(option.toString());
		}
	}

	public static void buildOptions() {
		options.add(new TransactionOption("New transaction"));
		options.add(new MergeAccountOption("Add new account"));
		exitOption = new ExitOption("Exit");
		options.add(exitOption);

	}

	public static void show(String message) {
		printStream.println(message);
	}

	public static String promptString() {
		String input = "";
		input = scanner.nextLine();
		scanner.close();
		return input;
	}

	public static int promptInt() {
		int input = 0;
		input = scanner.nextInt();
		scanner.nextLine();
		return input;
	}
}
