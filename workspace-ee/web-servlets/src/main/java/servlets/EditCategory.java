package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TermCategory;
import impl.TermCategoryJpaRepositorey;

@WebServlet(urlPatterns = "/editCategory")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermCategoryJpaRepositorey categoryJpaRepository;
	private static final String CATEGORIES = "categories";
	private static final String CATEGORY2 = "category";
	private static final String ERROR = "error";
	private static final String ZERO = "0";

	@Override
	public void init() throws ServletException {
		categoryJpaRepository = new TermCategoryJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		validateException(req);
		req.setAttribute("body", "/WEB-INF/views/edit-category.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			editCategory(req);
		} catch (Exception e) {
			req.getSession().setAttribute(ERROR, e.getClass().getName());
		}
		resp.sendRedirect(req.getServletContext().getContextPath() + "/manageCategories");
	}

	private void editCategory(HttpServletRequest req) {
		TermCategory category = new TermCategory();
		category.setCatName(req.getParameter(CATEGORIES));
		category.setAllowChildren(req.getParameter(CATEGORY2).equals(ZERO) ? Boolean.FALSE : Boolean.TRUE);
		categoryJpaRepository.editCategory(category);
	}

	private void validateException(HttpServletRequest req) {
		Object attribute = req.getSession().getAttribute(ERROR);
		req.setAttribute(ERROR, attribute);
		req.getSession().removeAttribute(ERROR);
	}

}
