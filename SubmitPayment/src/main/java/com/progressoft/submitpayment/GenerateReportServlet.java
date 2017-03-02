package com.progressoft.submitpayment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;

import com.progressoft.jip.context.AppContext;
import com.progressoft.jip.context.AppContextImpl;
import com.progressoft.jip.usecase.CreateReportUseCase;
import com.progressoft.jip.usecase.LoadAccountsUseCase;
import com.progressoft.jip.utilities.DataBaseSettings;

public class GenerateReportServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	private CreateReportUseCase createReportUseCase;
	private LoadAccountsUseCase loadAccountsUseCase;

	@Override
	public void init(ServletConfig config) throws ServletException {
		BasicDataSource dataSource = connectionConfiguration();
		AppContext context = new AppContextImpl(dataSource);
		createReportUseCase = new CreateReportUseCase(context.paymentRequestRepositroy());
		loadAccountsUseCase = new LoadAccountsUseCase(context.accountRepository());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		String accountIBAN = req.getParameter("accountIBAN");
		if (accountIBAN != null) {
			String reportFormat = req.getParameter("reportFormat");

			if (reportFormat.equalsIgnoreCase("XML")) {
				createReportUseCase.generateXmlRepotForAccount(accountIBAN, ps);
			} else {
				createReportUseCase.generateCsvReportForAccount(accountIBAN, ps);
			}
			String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			
			req.setAttribute("reportFormat", content);
			req.setAttribute("pageContent", "/WEB-INF/views/displayReport.jsp");
		} else {
			req.setAttribute("pageContent", "/WEB-INF/views/generateReport.jsp");
		}
		req.setAttribute("accounts", loadAccountsUseCase.loadAccounts());
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}


	private BasicDataSource connectionConfiguration() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DataBaseSettings.getInstance().driverClassName());
		dataSource.setUsername(DataBaseSettings.getInstance().username());
		dataSource.setPassword(DataBaseSettings.getInstance().password());
		dataSource.setUrl(DataBaseSettings.getInstance().url());
		return dataSource;
	}
}
