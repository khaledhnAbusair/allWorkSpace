package hr.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.dao.JDBCLocationDao;
import hr.dao.locationDao;
import hr.domain.LocationInformation;

@WebServlet(urlPatterns = "/api/locations")
public class LocationRestServlet extends HttpServlet {
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
		resp.setContentType("text/json");
		Iterable<LocationInformation> listAllLocation = locationDao.listAllLocation();
		Gson gson = new GsonBuilder().create();
		PrintWriter printWriter = resp.getWriter();
		gson.toJson(listAllLocation, printWriter);
		printWriter.flush();

	}
}
