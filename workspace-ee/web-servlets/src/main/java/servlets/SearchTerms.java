package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Term;
import entity.TermCategory;
import impl.TermCategoryJpaRepositorey;
import impl.TermJpaRepositorey;

@WebServlet(urlPatterns = "/searchTerm")
public class SearchTerms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOAD_CATEGORIES = "loadCategories";
	private static final String SEARCH_QUERY = "searchQuery";
	private static final String PURPOSE = "purpose";
	private static final String CATEGORY = "category";
	private static final String ERROR = "error";
	private static final String LABEL = "label";
	private static final String BODY = "body";
	private static final String NAME = "name";
	private TermJpaRepositorey termRepository;
	private TermCategoryJpaRepositorey categoryJpaRepository;

	@Override
	public void init() throws ServletException {
		categoryJpaRepository = new TermCategoryJpaRepositorey();
		termRepository = new TermJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		validateException(req);
		Collection<TermCategory> loadCategories = categoryJpaRepository.loadCategories();
		req.setAttribute(LOAD_CATEGORIES, loadCategories);
		forwordPage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Term searchTerm = populateTerm(req);
			TermCategory categorySearch = populateCategory(req, searchTerm);
			setQueryAttribute(req, searchTerm, categorySearch);
		} catch (Exception e) {
			req.getSession().setAttribute(ERROR, e.getClass().getName());
		}
		forwordPage(req, resp);
	}

	private TermCategory populateCategory(HttpServletRequest req, Term searchTerm) {
		TermCategory categorySearch = new TermCategory();
		categorySearch.setCatName(req.getParameter(CATEGORY));
		searchTerm.setTermCategory(categorySearch);
		return categorySearch;
	}

	private Term populateTerm(HttpServletRequest req) {
		Term searchTerm = new Term();
		searchTerm.setTermName(req.getParameter(NAME));
		searchTerm.setTermLabel(req.getParameter(LABEL));
		searchTerm.setTermPurpose(req.getParameter(PURPOSE));
		return searchTerm;
	}

	private void setQueryAttribute(HttpServletRequest req, Term searchTerm, TermCategory categorySearch) {
		List<Term> searchQuery = termRepository.buildSearchQuery(searchTerm, categorySearch);
		req.setAttribute(SEARCH_QUERY, searchQuery);
	}

	private void forwordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(BODY, "/WEB-INF/views/search-terms.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	private void validateException(HttpServletRequest req) {
		Object attribute = req.getSession().getAttribute(ERROR);
		req.setAttribute(ERROR, attribute);
		req.getSession().removeAttribute(ERROR);
	}
}
