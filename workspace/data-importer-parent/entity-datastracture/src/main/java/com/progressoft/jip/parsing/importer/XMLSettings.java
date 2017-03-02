package com.progressoft.jip.parsing.importer;

import java.nio.file.Path;

public class XMLSettings {
	private Path successPath;
	private Path sourcePath;
	private Path errorPath;
	

	public Path getSuccessPath() {
		return successPath;
	}

	public void setSuccessPath(Path successPath) {
		this.successPath = successPath;
	}

	public Path getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(Path sourcePath) {
		this.sourcePath = sourcePath;
	}

	public Path getErrorPath() {
		return errorPath;
	}

	public void setErrorPath(Path errorPath) {
		this.errorPath = errorPath;
	}

}
