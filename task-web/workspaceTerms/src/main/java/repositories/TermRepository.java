package repositories;

import java.util.Collection;
import java.util.List;

import entity.Term;
import entity.TermCategory;

public interface TermRepository {

	public void addTerm(Term term);

	public void deleteTerm(Term term);

	public void updateTerm(Term term);

	public Collection<Term> loadTerms();

	public List<Term> buildSearchQuery(Term term, TermCategory category);

	public Term getParentRootTerm();

}
