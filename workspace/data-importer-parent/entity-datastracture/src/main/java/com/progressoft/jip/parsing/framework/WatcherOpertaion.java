package com.progressoft.jip.parsing.framework;

import java.nio.file.Path;

public interface WatcherOpertaion {

	public void parseFile(Path file);

	public void checkIfDirctoryContainsFile();
}
