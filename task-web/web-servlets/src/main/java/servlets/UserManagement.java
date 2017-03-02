package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Term;
import entity.TermCategory;
import impl.TermCategoryJpaRepositorey;
import impl.TermJpaRepositorey;

@WebServlet(urlPatterns = "/userManagment")
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHILD_CATEGORY = "childCategory";
	private static final String TERM_CATEGORY = "termCategory";
	private static final String CHILD_PURPOSE = "childPurpose";
	private static final String TERM_PURPOSE = "termPurpose";
	private static final String CHILD_LABEL = "childLabel";
	private static final String TERM_LABEL = "termLabel";
	private static final String CHILD_NAME = "childName";
	private static final String PARENT_ID = "parentId";
	private static final String ADD_CHILD = "addChild";
	private static final String TERM_NAME = "termName";
	private static final String CAT_NAME = "catName";
	private static final String PURPOSE = "purpose";
	private static final String TERM_ID = "termId";
	private static final String DELETE = "delete";
	private static final String ACTION = "action";
	private static final String ERROR = "error";
	private static final String LABEL = "label";
	private static final String BODY = "body";
	private static final String NAME = "name";
	private static final String SAVE = "save";
	private static final String ID = "id";

	private TermJpaRepositorey termRepository;
	private TermCategoryJpaRepositorey categoryJpaRepository;
	private String id;

	@Override
	public void init() throws ServletException {
		categoryJpaRepository = new TermCategoryJpaRepositorey();
		termRepository = new TermJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		id = req.getParameter(ID);
		setAttributesIsExists(req);
		validateException(req);
		loadCategories(req);
		req.setAttribute(BODY, "/WEB-INF/views/user-management.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			saveOperation(req);
			deleteOperation(req);
			addChildOperation(req);
		} catch (Exception e) {
			req.getSession().setAttribute(ERROR, e.getClass().getName());
		}
		resp.sendRedirect(req.getServletContext().getContextPath() + "/userManagment");
	}

	private void addChildOperation(HttpServletRequest req) {
		if (req.getParameter(ACTION).equals(ADD_CHILD)) {
			insertTerm(req);
		}
	}

	private void deleteOperation(HttpServletRequest req) {
		if (req.getParameter(ACTION).equals(DELETE)) {
			deleteFotTerm(req);
		}
	}

	private void saveOperation(HttpServletRequest req) {
		if (req.getParameter(ACTION).equals(SAVE)) {
			updateForTerm(req);
		}
	}

	private void loadCategories(HttpServletRequest req) {
		Collection<TermCategory> loadCategories = categoryJpaRepository.loadCategories();
		req.setAttribute("loadCategories", loadCategories);
	}

	private void deleteFotTerm(HttpServletRequest req) {
		Term term = new Term();
		term.setTermId(Integer.parseInt(req.getParameter(PARENT_ID)));
		termRepository.deleteTerm(term);
	}

	private void validateException(HttpServletRequest req) {
		Object attribute = req.getSession().getAttribute(ERROR);
		req.setAttribute(ERROR, attribute);
		req.getSession().removeAttribute(ERROR);
	}

	private void setAttributesIsExists(HttpServletRequest req) {
		if (Objects.nonNull(req.getParameter(ID))) {
			Term term = termRepository.loadTermById(Integer.parseInt(req.getParameter(ID)));
			req.setAttribute(NAME, term.getTermName());
			req.setAttribute(CAT_NAME, term.getTermCategory().getCatName());
			req.setAttribute(LABEL, term.getTermLabel());
			req.setAttribute(PURPOSE, term.getTermPurpose());
			req.setAttribute(TERM_ID, term.getTermId());
		}
	}

	private void updateForTerm(HttpServletRequest req) {
		TermCategory category = new TermCategory();
		category.setCatName(req.getParameter(TERM_CATEGORY));
		Term term = new Term();
		term.setTermName(req.getParameter(TERM_NAME));
		term.setTermLabel(req.getParameter(TERM_LABEL));
		term.setTermPurpose(req.getParameter(TERM_PURPOSE));
		term.setTermCategory(category);
		term.setTermId(Integer.parseInt(req.getParameter(PARENT_ID)));
		termRepository.updateTerm(term);
	}

	private void insertTerm(HttpServletRequest req) {
		TermCategory category = new TermCategory();
		category.setCatName(req.getParameter(CHILD_CATEGORY));
		Term term = new Term();
		term.setTermName(req.getParameter(CHILD_NAME));
		term.setTermLabel(req.getParameter(CHILD_LABEL));
		term.setTermPurpose(req.getParameter(CHILD_PURPOSE));
		term.setTermCategory(category);
		if (Objects.isNull(id)) {
			term.setTerm(termRepository.getParentRootTerm());
		} else {
			Term parentTerm = new Term();
			parentTerm.setTermId(Integer.parseInt(id));
			term.setTerm(parentTerm);
		}
		termRepository.addTerm(term);
	}
}
