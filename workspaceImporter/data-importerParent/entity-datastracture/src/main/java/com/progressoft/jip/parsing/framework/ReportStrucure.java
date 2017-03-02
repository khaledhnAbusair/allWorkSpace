package com.progressoft.jip.parsing.framework;

public interface ReportStrucure {

	public void generateReport(String msgType, Exception e);

	public void createReportStracture();

	public void checkFileResultType(String msgType);
}
