package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Term;
import impl.TermJpaRepositorey;

@WebServlet(urlPatterns = "/treeStructure")
public class TermsTreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CATEGORY = "\",\"category\":\"";
	private static final String PURPOSE = "\",\"purpose\":\"";
	private static final String LABEL = "\",\"label\":\"";
	private static final String NODES = ",\"nodes\":[";
	private static final String BACKWARD_SLASH = "\",";
	private static final String RIGHT_BRACKETS = "]";
	private static final String HREF = "\"href\":\"";
	private static final String TEXT = "\"text\":\"";
	private static final String NAME = "\"name\":\"";
	private static final String RIGHT_CURLY = "\"}";
	private static final String LEFT_BRACKETS = "}";
	private static final String LEFT_CURLY = "{";
	private static final String EMPTY_QUTES = "";
	private static final String SLASH = "\"";
	private static final String COMMA = ",";
	private TermJpaRepositorey termRepository;

	@Override
	public void init() throws ServletException {
		termRepository = new TermJpaRepositorey();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(200);
		if (Objects.isNull(req.getParameter("depth"))) {
			fillData(req, resp);
		} else {
			int depth = Integer.parseInt(req.getParameter("depth"));
			Term rootTerm = termRepository.getParentRootTerm();
			resp.setContentType("application/json");
			PrintWriter writer = resp.getWriter();
			writer.write(adaptForJSONFormat(rootTerm, depth, 0));
			writer.flush();
		}
	}

	private void fillData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Term termById = termRepository.loadTermById(Integer.parseInt(req.getParameter("id")));
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.write(LEFT_CURLY);
		writer.write(NAME);
		writer.write(termById.getTermName());
		writer.write(LABEL);
		writer.write(termById.getTermLabel());
		writer.write(PURPOSE);
		writer.write(termById.getTermPurpose());
		writer.write(CATEGORY);
		writer.write(termById.getTermCategory().getCatName());
		writer.write(RIGHT_CURLY);
		writer.flush();
	}

	private String adaptForJSONFormat(Term term, int depth, int defualtDepth) {
		if (depth < 0)
			return EMPTY_QUTES;
		String jsonObj = LEFT_CURLY;
		jsonObj += TEXT + term.getTermName() + BACKWARD_SLASH;
		jsonObj += HREF + getHref(term, defualtDepth) + SLASH;
		if (Objects.nonNull(term.getTerms())) {
			if (!term.getTerms().isEmpty() && depth != 0) {
				jsonObj += NODES;
				Iterator<Term> iterator = term.getTerms().iterator();
				while (iterator.hasNext()) {
					jsonObj += adaptForJSONFormat(iterator.next(), depth - 1, defualtDepth + 1);
					if (iterator.hasNext()) {
						jsonObj += COMMA;
					}
				}
				jsonObj += RIGHT_BRACKETS;
			}
		}
		jsonObj += LEFT_BRACKETS;
		return jsonObj;
	}

	private String getHref(Term term, int depth) {
		return "./treeStructure?depth=" + String.valueOf(depth + 1) + "&id=" + term.getTermId();
	}
}
