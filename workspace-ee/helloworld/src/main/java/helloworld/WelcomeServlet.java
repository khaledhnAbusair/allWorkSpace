package helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("Welcome servlet is initilized");
		ServletConfig servletConfig = getServletConfig();
		String servletName = servletConfig.getServletName();
		System.out.println(servletName);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String requsterName = req.getParameter("requesterName");

		if (requsterName == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter requestName is required");
			return;
		}

		writer.print("<html>");
		writer.print("<body>");
		writer.println("Hello Mr Khaled Abusair :<b>");
		writer.println();
		writer.print("</b>");

		getHeader(req, writer);
		writer.print("</table>");

		printParameter(req, writer);
		writer.print("</body>");
		writer.print("</html>");
		writer.flush();

	}

	private void getHeader(HttpServletRequest req, PrintWriter writer) {
		Enumeration<String> headerNames = req.getHeaderNames();
		writer.print("<table>");
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			writer.println("<tr>");
			writer.println("<td>" + headerName + "</td>");
			writer.println("<td>" + req.getHeader(headerName) + "</td>");
			writer.println("</tr>");
		}
	}

	private void printParameter(HttpServletRequest req, PrintWriter writer) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		for (String string : keySet) {
			writer.println("Name : " + string.toString() + " values" + Arrays.toString(req.getParameterValues(string)));
		}
	}

}
