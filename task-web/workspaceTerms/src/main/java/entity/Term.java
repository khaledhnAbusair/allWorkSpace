package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "terms")
@NamedQuery(name = "Term.findAll", query = "SELECT t FROM Term t")
public class Term implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer termId;

	private String termLabel;

	private String termName;

	private String termPurpose;

	@ManyToOne
	@JoinColumn(name = "parentTerm")
	private Term term;

	@OneToMany(mappedBy = "term")
	private List<Term> terms;

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private TermCategory termCategory;

	public Integer getTermId() {
		return this.termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public String getTermLabel() {
		return this.termLabel;
	}

	public void setTermLabel(String termLabel) {
		this.termLabel = termLabel;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTermPurpose() {
		return this.termPurpose;
	}

	public void setTermPurpose(String termPurpose) {
		this.termPurpose = termPurpose;
	}

	public Term getTerm() {
		return this.term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public List<Term> getTerms() {
		return this.terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public Term addTerm(Term term) {
		getTerms().add(term);
		term.setTerm(this);

		return term;
	}

	public Term removeTerm(Term term) {
		getTerms().remove(term);
		term.setTerm(null);

		return term;
	}

	public TermCategory getTermCategory() {
		return this.termCategory;
	}

	public void setTermCategory(TermCategory termCategory) {
		this.termCategory = termCategory;
	}
}