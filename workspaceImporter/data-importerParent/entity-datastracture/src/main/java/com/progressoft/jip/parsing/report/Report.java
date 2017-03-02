// package com.progressoft.jip.parsing.report;
//
// import static com.progressoft.jip.parsing.framework.Constant.FAILED;
// import static com.progressoft.jip.parsing.framework.Constant.PARTIALLY;
// import static com.progressoft.jip.parsing.framework.Constant.SUCCESS;
//
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.sql.BatchUpdateException;
// import java.sql.SQLException;
// import java.time.LocalDateTime;
//
// import javax.xml.stream.XMLStreamException;
//
// public class Report {
//
// private LocalDateTime startParsingTime;
// private LocalDateTime endParsingTime;
// private String errorMessage;
// private GenrateReport genrateReport;
//
// public Report() {
// genrateReport = new GenrateReport();
// }
//
// public static String getSuccess() {
// return SUCCESS;
// }
//
// public static String getPartially() {
// return PARTIALLY;
// }
//
// public static String getFailed() {
// return FAILED;
// }
//
// public LocalDateTime getStartTime() {
// return startParsingTime;
// }
//
// public void setStartTime(LocalDateTime startTime) {
// this.startParsingTime = startTime;
// }
//
// public LocalDateTime getEndTime() {
// return endParsingTime;
// }
//
// public void setEndTime(LocalDateTime endTime) {
// this.endParsingTime = endTime;
// }
//
// public String getErrorMessage() {
// return errorMessage;
// }
//
// public void setErrorMessage(String errorMessage) {
// this.errorMessage = errorMessage;
// }
//
// public void identifyExceptionType(Exception e) {
// if (e instanceof SQLException || e instanceof XMLStreamException || e
// instanceof InterruptedException) {
// genrateReport.generateReport(getFailed(), e);
// } else if (e instanceof BatchUpdateException) {
// genrateReport.generateReport(getPartially(), e);
// } else {
// genrateReport.generateReport(getSuccess(), e);
// }
// createPathDirctories();
// }
//
// private void createPathDirctories() {
// Path path = Paths.get(""); // TODO
// if (!Files.exists(path)) {
// try {
// Files.createDirectories(path);
// } catch (IOException e) {
// throw new IllegalStateException(e);
// }
// }
// }
//
// }
