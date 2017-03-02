package hr.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/locations-map")
public class LocationMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("content", "/WEB-INF/Views/locations-map.jsp");
		req.setAttribute("additionalfooter", "/WEB-INF/Views/locations-map-footer.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Views/base.jsp");
		dispatcher.forward(req, resp);
	}

}
