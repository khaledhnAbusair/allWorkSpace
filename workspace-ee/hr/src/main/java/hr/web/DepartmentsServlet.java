package hr.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp2.BasicDataSource;

import hr.dao.DaoException;
import hr.dao.DepartmentsDao;
import hr.dao.JDBCDepartmentDao;
import hr.domain.DepartmentsInfo;

public class DepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentsDao departmentDao;

	@Override
	public void init() throws ServletException {
		BasicDataSource dataSource = confidurationDataSource();
		departmentDao = new JDBCDepartmentDao(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Iterable<DepartmentsInfo> all = departmentDao.listAll();
		req.setAttribute("departmentsList", all);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Views/departments-view.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DepartmentsInfo deptInfo = createDepartmentInfoFormat(req);
		try {

			departmentDao.addDepartment(deptInfo);
			Cookie cookie = getCookies(req);
			cookie.setDomain(".induction.com");
			cookie.setValue((Integer.valueOf(cookie.getValue()) + 1) + "");
			cookie.setHttpOnly(true);
			cookie.setSecure(false);
			resp.addCookie(cookie);
			resp.sendRedirect("/departments");

		} catch (DaoException e) {
			Iterable<DepartmentsInfo> all = departmentDao.listAll();
			req.setAttribute("errorMessage", e.getMessage());
			req.setAttribute("departmentsList", all);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Views/departments-view.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private Cookie getCookies(HttpServletRequest req) {
		// only available in request , not available to script codes running
		// on browser
		Cookie cookie = new Cookie("post-counts", "0");

		Cookie[] cookies = req.getCookies();
		for (Cookie co : cookies) {
			if (co.getName().equals("post-counts")) {
				cookie = co;
				break;
			}
		}
		return cookie;
	}

	private DepartmentsInfo createDepartmentInfoFormat(HttpServletRequest req) throws ServletException {
		DepartmentsInfo deptInfo;
		try {
			deptInfo = new DepartmentsInfo();
			BeanUtils.populate(deptInfo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);

		}
		return deptInfo;
	}

	private BasicDataSource confidurationDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		ServletConfig servletConfig = getServletConfig();
		dataSource.setUsername(servletConfig.getInitParameter("db.username"));
		dataSource.setPassword(servletConfig.getInitParameter("db.password"));
		dataSource.setUrl(servletConfig.getInitParameter("db.url"));
		dataSource.setDriverClassName(servletConfig.getInitParameter("db.driver"));
		return dataSource;
	}
}
