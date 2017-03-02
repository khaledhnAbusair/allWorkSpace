package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "term_category")
@NamedQuery(name = "TermCategory.findAll", query = "SELECT t FROM TermCategory t")
public class TermCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cat_name")
	private String catName;

	@Column(name = "allow_children")
	private Boolean allowChildren;

	@OneToMany(mappedBy = "termCategory")
	private List<Term> terms;

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Boolean getAllowChildren() {
		return this.allowChildren;
	}

	public void setAllowChildren(Boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public List<Term> getTerms() {
		return this.terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public Term addTerm(Term term) {
		getTerms().add(term);
		term.setTermCategory(this);

		return term;
	}

	public Term removeTerm(Term term) {
		getTerms().remove(term);
		term.setTermCategory(null);

		return term;
	}

}