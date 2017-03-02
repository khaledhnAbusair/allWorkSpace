package hr.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import hr.dao.JDBCLocationDao;
import hr.dao.locationDao;
import hr.domain.LocationInformation;

@WebServlet(urlPatterns = "/locations")
public class LocationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private locationDao locationDao;

	@Override
	public void init() throws ServletException {
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/maps");
			locationDao = new JDBCLocationDao(dataSource);
		} catch (NamingException e) {
			throw new ServletException(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Iterable<LocationInformation> listAllLocation = locationDao.listAllLocation();
		req.setAttribute("locationsList", listAllLocation);
		req.getRequestDispatcher("/WEB-INF/Views/locations.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parameterMap = req.getParameterMap();
		LocationInformation locationInformation = new LocationInformation();
		try {
			BeanUtils.populate(locationInformation, parameterMap);
			locationDao.addLocation(locationInformation);
			resp.sendRedirect("/locations");
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}
}
