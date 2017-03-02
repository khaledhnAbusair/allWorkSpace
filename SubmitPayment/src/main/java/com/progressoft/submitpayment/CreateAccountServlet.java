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
import com.progressoft.jip.datastructures.AccountDatastructure;
import com.progressoft.jip.usecase.CreateAccountUseCase;
import com.progressoft.jip.usecase.LoadCurrenciesUseCase;
import com.progressoft.jip.utilities.DataBaseSettings;

public class CreateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CreateAccountUseCase createAccountUseCase;

	private LoadCurrenciesUseCase loadCurrenciesUseCase;

	@Override
	public void init() throws ServletException {
		BasicDataSource dataSource = connectionConfiguration();
		AppContext context = new AppContextImpl(dataSource);
		loadCurrenciesUseCase = new LoadCurrenciesUseCase(context.currencyRepository());
		createAccountUseCase = new CreateAccountUseCase(context.accountRepository());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("currencies", loadCurrenciesUseCase.loadCurrencies());
		req.setAttribute("pageContent", "/WEB-INF/views/createAccount.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountDatastructure datastructure;
		try {
			datastructure = new AccountDatastructure();
			BeanUtils.populate(datastructure, req.getParameterMap());
			createAccountUseCase.createAccount(datastructure);
			resp.sendRedirect("/createAccount");
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
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
