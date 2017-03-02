package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Term;
import entity.TermCategory;
import exceptions.CannotAddTermWithRootNameException;
import exceptions.DublicateTermNameException;
import exceptions.NoParentRootIsExistException;
import exceptions.CategoryNotFoundException;
import exceptions.CategoryReferencedException;
import exceptions.TermConnotBeDeletedException;
import exceptions.TermIsAlreadyExistException;
import loader.EntityManagerLoader;
import repositories.TermRepository;

public class TermJpaRepositorey implements TermRepository {

	private static final String TERM_CATEGORY = "termCategory";
	private static final String TERM_PURPOSE = "termPurpose";
	private static final String TERM_FIND_ALL = "Term.findAll";
	private static final String TERM_LABEL = "termLabel";
	private static final String TERM_NAME = "termName";
	private static final String LIKE_EXPRESSION = "%";
	private static final String CAT_NAME = "catName";
	private static final int ROOT_PARENT_ID = 8;
	private static final String ROOT = "Root";
	private EntityManager entityManager;

	public TermJpaRepositorey() {
		entityManager = EntityManagerLoader.getEntityManger();
	}

	public TermJpaRepositorey(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Term loadTermById(int termId) {
		Term find = entityManager.find(Term.class, termId);
		if (Objects.isNull(find))
			throw new CategoryNotFoundException();
		return find;
	}

	@Override
	public Term getParentRootTerm() {
		Term find = entityManager.find(Term.class, ROOT_PARENT_ID);
		if (Objects.isNull(find))
			throw new NoParentRootIsExistException();
		return find;
	}

	@Override
	public void addTerm(Term term) {
		boolean commited = false;
		try {
			entityManager.getTransaction().begin();
			if (term.getTermName().equalsIgnoreCase(ROOT))
				throw new CannotAddTermWithRootNameException();
			if (Objects.nonNull(term.getTerm())) {
				Term parentTerm = entityManager.find(Term.class, term.getTerm().getTermId());
				List<Term> terms = parentTerm.getTerms();
				checkNameUniqueness(term, terms);
			}
			entityManager.persist(term);
			commited = true;
		} finally {
			if (!commited)
				entityManager.getTransaction().rollback();
			else
				entityManager.getTransaction().commit();
		}
	}

	@Override
	public void updateTerm(Term term) {
		boolean commited = false;
		try {
			entityManager.getTransaction().begin();
			Term find = entityManager.find(Term.class, term.getTermId());
			if (Objects.nonNull(find)) {
				entityManager.merge(term);
				entityManager.getTransaction().commit();
				commited = true;
			} else
				throw new IllegalStateException("Cannot Find Record");
		} finally {
			if (!commited)
				entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void deleteTerm(Term term) {
		Term find = entityManager.find(Term.class, term.getTermId());
		if (Objects.isNull(find))
			throw new TermConnotBeDeletedException();

		validateTermIfYouHavetheChild(find);
		entityManager.getTransaction().begin();
		entityManager.remove(find);
		entityManager.getTransaction().commit();
	}

	@Override
	public Collection<Term> loadTerms() {
		TypedQuery<Term> typedQuery = entityManager.createNamedQuery(TERM_FIND_ALL, Term.class);
		return typedQuery.getResultList();
	}

	@Override
	public List<Term> buildSearchQuery(Term term, TermCategory category) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Term> criteriaQuery = builder.createQuery(Term.class);
		Root<Term> termRoot = criteriaQuery.from(Term.class);
		List<Predicate> predicates = new ArrayList<>();
		criteriaQuery.select(termRoot);
		checkTermNameIsNullOrEmpty(term, builder, termRoot, predicates);
		checkTermLabelIsNullOrEmpty(term, builder, termRoot, predicates);
		checkTermPurposeIsNullOrEmpty(term, builder, termRoot, predicates);
		checkCategoryIsNullOrEmpty(category, builder, termRoot, predicates);

		criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		TypedQuery<Term> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	private void validateTermIsExist(Term find) {
		if (Objects.nonNull(find))
			throw new TermIsAlreadyExistException();
	}

	private void validateTermIfYouHavetheChild(Term term) {
		if (!term.getTerms().isEmpty()) {
			throw new CategoryReferencedException();
		}
	}

	private void checkNameUniqueness(Term termToAdd, List<Term> terms) {
		for (Term termBrothers : terms) {
			if (termToAdd.getTermName().equalsIgnoreCase(termBrothers.getTermName())) {
				throw new DublicateTermNameException();
			}
		}
	}

	private void checkCategoryIsNullOrEmpty(TermCategory category, CriteriaBuilder builder, Root<Term> termRoot,
			List<Predicate> predicates) {
		if (category != null && category.getCatName() != null) {
			predicates.add(builder.like(builder.upper(termRoot.get(TERM_CATEGORY).get(CAT_NAME)),
					LIKE_EXPRESSION + category.getCatName() + LIKE_EXPRESSION));
		}
	}

	private void checkTermPurposeIsNullOrEmpty(Term term, CriteriaBuilder builder, Root<Term> termRoot,
			List<Predicate> predicates) {
		if (term.getTermPurpose() != null && !(term.getTermPurpose().isEmpty())) {
			predicates.add(builder.like(builder.upper(termRoot.get(TERM_PURPOSE)),
					LIKE_EXPRESSION + term.getTermPurpose().toUpperCase() + LIKE_EXPRESSION));
		}
	}

	private void checkTermLabelIsNullOrEmpty(Term term, CriteriaBuilder builder, Root<Term> termRoot,
			List<Predicate> predicates) {
		if (term.getTermLabel() != null && !(term.getTermLabel().isEmpty())) {
			predicates.add(builder.like(builder.upper(termRoot.get(TERM_LABEL)),
					LIKE_EXPRESSION + term.getTermLabel().toUpperCase() + LIKE_EXPRESSION));
		}
	}

	private void checkTermNameIsNullOrEmpty(Term term, CriteriaBuilder builder, Root<Term> termRoot,
			List<Predicate> predicates) {
		if (term.getTermName() != null && !(term.getTermName().isEmpty())) {
			predicates.add(builder.like(builder.upper(termRoot.get(TERM_NAME)),
					LIKE_EXPRESSION + term.getTermName().toUpperCase() + LIKE_EXPRESSION));
		}
	}
}
