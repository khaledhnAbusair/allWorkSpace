package com.progressoft.jip.bankapplication.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class PathFileChange extends IOException {

	public PathFileChange(String message) {
		super(message);
	}
}
