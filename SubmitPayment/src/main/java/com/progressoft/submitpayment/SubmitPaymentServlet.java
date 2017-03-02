package com.progressoft.submitpayment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;

import com.progressoft.jip.context.AppContext;
import com.progressoft.jip.context.AppContextImpl;
import com.progressoft.jip.datastructures.PaymentRequestDataStructure;
import com.progressoft.jip.model.exception.AccountRuleViolationException;
import com.progressoft.jip.usecase.CreateNewPaymentRequestUseCase;
import com.progressoft.jip.usecase.LoadAccountsUseCase;
import com.progressoft.jip.usecase.LoadCurrenciesUseCase;
import com.progressoft.jip.usecase.LoadPaymentPurposesUseCase;
import com.progressoft.jip.utilities.DataBaseSettings;

public class SubmitPaymentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LoadAccountsUseCase loadAccountsUseCase;
	private CreateNewPaymentRequestUseCase createPaymentUseCase;
	private LoadCurrenciesUseCase loadCurrenciesUseCase;
	private LoadPaymentPurposesUseCase loadPaymentPurposesUseCase;

	@Override
	public void init() throws ServletException {
		BasicDataSource dataSource = connectionConfiguration();
		AppContext context = new AppContextImpl(dataSource);
		loadAccountsUseCase = new LoadAccountsUseCase(context.accountRepository());
		createPaymentUseCase = new CreateNewPaymentRequestUseCase(context.paymentRequestRepositroy());
		loadCurrenciesUseCase = new LoadCurrenciesUseCase(context.currencyRepository());
		loadPaymentPurposesUseCase = new LoadPaymentPurposesUseCase(context.paymentPurposeRepository());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("paymentPurposes", loadPaymentPurposesUseCase.loadPaymentPurposes());
		req.setAttribute("currencies", loadCurrenciesUseCase.loadCurrencies());
		req.setAttribute("accounts", loadAccountsUseCase.loadAccounts());

		req.setAttribute("pageContent", "/WEB-INF/views/createPayment.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PaymentRequestDataStructure dataStructure;
		try {
			dataStructure = new PaymentRequestDataStructure();
			BeanUtils.populate(dataStructure, req.getParameterMap());
			createPaymentUseCase.createPaymentRequestFrom(dataStructure);
			resp.sendRedirect("/submitPayment");
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (AccountRuleViolationException e) {
			e.printStackTrace();
		}

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
