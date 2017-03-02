//package com.progressoft.jip.parsing.report;
//
//import static com.progressoft.jip.parsing.framework.Constant.ERROR_PATH;
//import static com.progressoft.jip.parsing.framework.Constant.PARTIALLY;
//
//import java.io.File;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//
//import com.progressoft.jip.parsing.framework.ReportStrucure;
//import com.progressoft.jip.parsing.importer.DataImporter;
//
//public class GenrateReport implements ReportStrucure {
//
//	private JAXBContext jaxbContext;
//	private Report reportBean;
//	private DataImporter importer;
//	private Marshaller marshaller;
//	private File file;
//
//	public GenrateReport() {
//	}
//
//	public void setElement(String msgType, Exception e) {
//		reportBean.setErrorMessage(e + " || " + msgType);
//		reportBean.setEndTime(LocalDateTime.now());
//		reportBean.setStartTime(LocalDateTime.now());
//	}
//
//	@Override
//	public void generateReport(String msgType, Exception e) {
//		setElement(msgType, e);
//		checkFileResultType(msgType);
//		createReportStracture();
//
//	}
//
//	@Override
//	public void createReportStracture() {
//		try {
//			jaxbContext = JAXBContext.newInstance(Report.class);
//			marshaller = jaxbContext.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.marshal(reportBean, file);
//		} catch (JAXBException e) {
//			throw new IllegalStateException(e);
//		}
//	}
//
//	@Override
//	public void checkFileResultType(String msgType) {
//		if (msgType.equals(ERROR_PATH) || msgType.equals(PARTIALLY)) {
//			file = Paths.get(importer.getErrorPath().toString()).toFile();
//		} else {
//			file = Paths.get(importer.getSuccessPath().toString()).toFile();
//		}
//	}
//}
