package servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TermCategory;
import exceptions.CategorySuperException;
import impl.TermCategoryJpaRepositorey;

@WebServlet(urlPatterns = "/manageCategories")
public class ManagerCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOAD_CATEGORIES = "loadCategories";
	private TermCategoryJpaRepositorey categoryJpaRepository;
	private static final String CATEGORY = "category";
	private static final String ERROR = "error";
	private static final String NAME = "name";
	private static final String BODY = "body";
	private static final String ZERO = "0";

	@Override
	public void init() throws ServletException {
		categoryJpaRepository = new TermCategoryJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO move this to a filer to check if the request is GET and to do
		// the behavior of moving the attribute from session to request
		Object attribute = req.getSession().getAttribute(ERROR);
		req.setAttribute(ERROR, attribute);
		req.getSession().removeAttribute(ERROR);

		Collection<TermCategory> loadCategories = categoryJpaRepository.loadCategories();
		req.setAttribute(LOAD_CATEGORIES, loadCategories);
		req.setAttribute(BODY, "/WEB-INF/views/manage-categories.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			addTermCategory(req);
		} catch (CategorySuperException e) {
			req.getSession().setAttribute(ERROR, e.getClass().getName());
		}
		resp.sendRedirect(req.getServletContext().getContextPath() + "/manageCategories");

	}

	private void addTermCategory(HttpServletRequest req) {
		TermCategory category = new TermCategory();
		category.setCatName(req.getParameter(NAME));
		category.setAllowChildren(req.getParameter(CATEGORY).equals(ZERO) ? Boolean.FALSE : Boolean.TRUE);
		categoryJpaRepository.addCategory(category);
	}
}
