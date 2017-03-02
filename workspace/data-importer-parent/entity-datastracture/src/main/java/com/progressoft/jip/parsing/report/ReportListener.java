package com.progressoft.jip.parsing.report;

import static com.progressoft.jip.parsing.framework.Constant.FAILED;
import static com.progressoft.jip.parsing.framework.Constant.PARTIALLY;
import static com.progressoft.jip.parsing.framework.Constant.SUCCESS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.progressoft.jip.parsing.framework.Listener;
import com.progressoft.jip.parsing.importer.XMLSettings;

public class ReportListener implements Listener {

	private Report reportBean = new Report();
	private JAXBContext jaxbContext;
	private Marshaller marshaller;
	private XMLSettings settings;
	private String fileName;
	private Path reportPath;
	private List<DataError> errors = new ArrayList<>();
	//private Path pathToReport;

	public ReportListener(XMLSettings settings) {
		this.settings = settings;
		this.reportPath = settings.getSuccessPath();
	}

	@Override
	public void generateReport() {
		createPathDirctories();
		formatFileName(reportPath, fileName);
		createReportStracture();
	}

	private void identifyExceptionType(Exception e) {
		if (e.getClass().equals(SQLException.class)) {
			reportBean.setResult(FAILED);
			reportPath = settings.getErrorPath();
			initReportBean(e, "import has failed");
		} else if (e.getClass().equals(BatchUpdateException.class)) {
			reportBean.setResult(PARTIALLY);
			reportPath = settings.getErrorPath();
			initReportBean(e, "some records failed");
		} else {
			reportBean.setResult(SUCCESS);
			initReportBean(e, null);
		}
	}

	private void createReportStracture() {
		try {
			jaxbContext = JAXBContext.newInstance(Report.class);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(reportBean, reportPath.toFile());
		} catch (JAXBException e) {
			throw new IllegalStateException(e);
		}
	}

	private void formatFileName(Path filePath, String fileName) {
		String name = fileName.toString().split("\\.")[0];
		reportPath = Paths.get(filePath.toString(), name + "-report.xml");
		
	}

	private void initReportBean(Exception e, String message) {
		reportBean.setErrorMessage(message);
		reportBean.setEndTime(LocalDateTime.now().toString());
	}

	private void createPathDirctories() {
		System.out.println("create directory "+reportPath);
		if (!Files.exists(reportPath)) {
			try {
				Files.createDirectories(reportPath);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	@Override
	public void updateListener(Exception e) {
		identifyExceptionType(e);
		DataError dataError = new DataError();
		dataError.setErrorMessage(e.getMessage());
		this.errors.add(dataError);
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
