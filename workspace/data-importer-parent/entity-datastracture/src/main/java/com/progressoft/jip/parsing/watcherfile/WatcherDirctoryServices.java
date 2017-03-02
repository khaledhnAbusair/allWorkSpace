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
import com.progressoft.jip.parsing.importer.XMLSettings;
import com.progressoft.jip.parsing.report.ReportListener;
import com.progressoft.jip.parsing.xml.XMLParserConnection;

public class WatcherDirctoryServices implements Runnable, WatcherOpertaion {

	private XMLParserConnection parserConnection;
	private XMLSettings xmlSettings;
	private CSVParser csvParser;
	private WatchKey key;
	private ReportListener genrateReport;

	public WatcherDirctoryServices(XMLSettings xmlSettings) {
		this.xmlSettings = xmlSettings;
		genrateReport = new ReportListener(xmlSettings);
	}

	@Override
	public void run() {
		checkIfDirctoryContainsFile();
		try {
			Path dirctory = xmlSettings.getSourcePath();
			WatchService watchService = FileSystems.getDefault().newWatchService();
			dirctory.register(watchService, ENTRY_CREATE);
			checkDirctory(watchService, dirctory);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void checkIfDirctoryContainsFile() {
		try {
			Files.list(xmlSettings.getSourcePath()).forEach(file -> {
				new Thread() {
					@Override
					public void run() {
						parseFile(file);
					}
				}.start();
			});
		} catch (IOException e1) {
			throw new IllegalStateException(e1);
		}
	}

	@Override
	public void parseFile(Path file) {
		if (file.toString().toLowerCase().endsWith(XML_EXTENTION)) {
			parserConnection = new XMLParserConnection(file);
			parserConnection.parseDocument(genrateReport);

		} else if (file.toString().toLowerCase().endsWith(CSV_EXTENTION)) {
			csvParser = new CSVParser(file);
			csvParser.parseDocument(genrateReport);
		}
	}

	private void checkDirctory(WatchService watchService, Path dirctory) throws InterruptedException {
		do {
			key = watchService.take();
			for (WatchEvent<?> watchEvent : key.pollEvents()) {
				if (OVERFLOW == watchEvent.kind()) {
					continue;
				} else if (ENTRY_CREATE == watchEvent.kind()) {
					String fileName = watchEvent.context().toString();
					genrateReport.setFileName(fileName);
					new Thread() {
						@Override
						public void run() {
							parseFile(Paths.get(dirctory.toString(), fileName));
						}
					}.start();
				}
			}
		} while (key.reset());
	}
}
