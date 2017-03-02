package com.progressoft.jip.parsing.watcherfile;

import static com.progressoft.jip.parsing.framework.Constant.CSV_EXTENTION;
import static com.progressoft.jip.parsing.framework.Constant.XML_EXTENTION;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.progressoft.jip.parsing.csv.CSVParser;
import com.progressoft.jip.parsing.framework.WatcherOpertaion;
import com.progressoft.jip.parsing.xml.XMLParserConnection;

public class WatcherDirctoryServices implements Runnable, WatcherOpertaion {

	private XMLParserConnection parserConnection;
	private CSVParser csvParser;
	private String pathName;
	private WatchKey key;
	//private Report report;

	public WatcherDirctoryServices(String pathName) {
		this.pathName = pathName;
	}

	@Override
	public void run() {
		checkIfDirctoryContainsFile();
		try {
			Path dirctory = Paths.get(pathName);
			dirctory.register(FileSystems.getDefault().newWatchService(), ENTRY_CREATE);
			checkDirctory(FileSystems.getDefault().newWatchService(), dirctory);
		} catch (Exception e) {
			//report.identifyExceptionType(e);
		}
	}

	@Override
	public void checkIfDirctoryContainsFile() {
		try {
			Files.list(Paths.get(pathName)).forEach(file -> {
				new Thread() {
					@Override
					public void run() {
						parseFileWithCorrectParser(file);
					}
				}.start();
			});
		} catch (IOException e1) {
		//	report.identifyExceptionType(e1);
		}
	}

	@Override
	public void parseFileWithCorrectParser(Path file) {
		if (file.toString().toLowerCase().endsWith(XML_EXTENTION)) {
			parserConnection = new XMLParserConnection(file);
			parserConnection.parseDocument();
		} else if (file.toString().toLowerCase().endsWith(CSV_EXTENTION)) {
			csvParser = new CSVParser(file);
			csvParser.parseDocument();
		}
	}

	@SuppressWarnings("unchecked")
	private void checkDirctory(WatchService watchService, Path dirctory) throws InterruptedException {
		do {
			key = watchService.take();
			for (WatchEvent<?> watchEvent : key.pollEvents()) {
				if (OVERFLOW == watchEvent.kind()) {
					continue;
				} else if (ENTRY_CREATE == watchEvent.kind()) {
					Path fileName = ((WatchEvent<Path>) watchEvent).context();
					new Thread() {
						@Override
						public void run() {
							parseFileWithCorrectParser(dirctory.resolve(fileName));
						}
					}.start();
				}
			}
		} while (key.reset());
	}
}
