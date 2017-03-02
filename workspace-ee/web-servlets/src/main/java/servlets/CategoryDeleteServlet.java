package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TermCategory;
import exceptions.CategorySuperException;
import impl.TermCategoryJpaRepositorey;

@WebServlet(urlPatterns = "/deleteCategory")
public class CategoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermCategoryJpaRepositorey categoryJpaRepositorey;
	private static final String ERROR = "error";
	private static final String CHILD = "child";
	private static final String NAME = "name";
	private static final String BODY = "body";
	private static final String ZERO = "0";

	@Override
	public void init() throws ServletException {
		categoryJpaRepositorey = new TermCategoryJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		validateException(req);
		getSessionParameters(req);
		req.setAttribute(BODY, "/WEB-INF/views/delete-Category.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			deleteTermCategory(req);
		} catch (CategorySuperException e) {
			req.getSession().setAttribute(ERROR, e.getClass().getName());
		}
		resp.sendRedirect(req.getServletContext().getContextPath() + "/manageCategories");

	}

	public void deleteTermCategory(HttpServletRequest req) {
		TermCategory category = new TermCategory();
		category.setCatName(req.getParameter(NAME));
		category.setAllowChildren(req.getParameter(CHILD).equals(ZERO) ? Boolean.FALSE : Boolean.TRUE);
		categoryJpaRepositorey.deleteCategory(category);

	}

	private void getSessionParameters(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute(NAME, req.getParameter(NAME));
		session.setAttribute(CHILD, req.getParameter(CHILD));
	}

	private void validateException(HttpServletRequest req) {
		Object attribute = req.getSession().getAttribute(ERROR);
		req.setAttribute(ERROR, attribute);
		req.getSession().removeAttribute(ERROR);
	}
}
