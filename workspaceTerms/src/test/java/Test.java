import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import entity.Term;

public class Test {
	public static void main(String[] args) {
		Term term = new Term();
		Term child1 = new Term();
		Term child2 = new Term();

		term.setTermName("Jordan");
		child1.setTermName("amman");
		child2.setTermName("irbid");
		term.setTerms(Arrays.asList(child1, child2));

		System.out.println(convertToJSON(term, 1));
	}

	public static String convertToJSON(Term term, int depth) {
		if (depth < 0)
			return "";

		String json = "{";

		json += "\"text\":\"" + term.getTermName() + "\",";
		json += "\"href\":\"" + getHref(term, depth) + "\"";

		if (Objects.nonNull(term.getTerms())) {
			if (!term.getTerms().isEmpty() && depth != 0) {
				json += ",\"nodes\":[";
				Iterator<Term> iterator = term.getTerms().iterator();
				while (iterator.hasNext()) {
					json += convertToJSON(iterator.next(), depth - 1);
					if (iterator.hasNext()) {
						json += ",";
					}
				}
				json += "]";
			}
		}

		json += "}";
		return json;
	}

	private static String getHref(Term term, int depth) {
		return "./treeStructure?depth=" + String.valueOf(depth + 1);
	}
}
