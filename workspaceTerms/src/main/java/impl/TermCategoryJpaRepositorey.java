package impl;

import static constant.Constants.UPDATE_TERM_CATEGORY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.TermCategory;
import exceptions.CategoryReferencedException;
import exceptions.CategoryNotFoundException;
import exceptions.NoTermCategoryHasBeenUpdated;
import exceptions.TermCategoryIsAlreadyExistException;
import exceptions.RecordCannotBeDeleted;
import loader.EntityManagerLoader;
import repositories.TermCategoryRepositorey;

public class TermCategoryJpaRepositorey implements TermCategoryRepositorey {

	private static final String ROOT_TYPE = "Root type";
	private EntityManager entityManager;

	public TermCategoryJpaRepositorey() {
		entityManager = EntityManagerLoader.getEntityManger();
	}

	public TermCategoryJpaRepositorey(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

	@Override
	public void editCategory(TermCategory category) {

		boolean categoryEdit = false;
		TermCategory termCategory = entityManager.find(TermCategory.class, category.getCatName());
		validateCategoryIsExist(termCategory);
		// TODO you are updating the database but not the cache
		Query updateQuery = entityManager.createQuery(UPDATE_TERM_CATEGORY);
		populateCategoryForUpdate(category, updateQuery);
		try {
			entityManager.getTransaction().begin();
			int effectedRow = updateQuery.executeUpdate();
			validateEffectedRowUpdated(effectedRow);
			entityManager.getTransaction().commit();
			categoryEdit = true;
		} finally {
			if (!categoryEdit)
				entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void addCategory(TermCategory category) {
		TermCategory termCategory = populateTermCategory(category);
		entityManager.getTransaction().begin();
		boolean categoryAdd = false;
		try {
			TermCategory find = entityManager.find(TermCategory.class, termCategory.getCatName());
			validateCategoryIsAlreadyExist(find);
			entityManager.persist(category);
			entityManager.getTransaction().commit();
			categoryAdd = true;
		} finally {
			if (!categoryAdd)
				entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void deleteCategory(TermCategory category) {
		TermCategory find = entityManager.find(TermCategory.class, category.getCatName());
		entityManager.getTransaction().begin();
		boolean commited = false;
		try {

			if (Objects.isNull(find)) {
				throw new CategoryNotFoundException();
			}
			if (ROOT_TYPE.equals(find.getCatName())) {
				throw new RecordCannotBeDeleted();
			}
			if (find.getAllowChildren()) {
				throw new CategoryReferencedException();
			}
			entityManager.remove(find);
			entityManager.getTransaction().commit();
			commited = true;
		} finally {
			if (!commited && entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();

		}

	}

	@Override
	public Collection<TermCategory> loadCategories() {
		TypedQuery<TermCategory> typedQuery = entityManager.createNamedQuery("TermCategory.findAll",
				TermCategory.class);
		return typedQuery.getResultList();
	}

	private TermCategory populateTermCategory(TermCategory category) {
		TermCategory termCategory = new TermCategory();
		termCategory.setCatName(category.getCatName());
		termCategory.setAllowChildren(category.getAllowChildren());
		return termCategory;
	}

	private void validateCategoryIsAlreadyExist(TermCategory find) {
		if (Objects.nonNull(find))
			throw new TermCategoryIsAlreadyExistException();
	}

	private void validateCategoryIsExist(TermCategory termCategory) {
		if (Objects.isNull(termCategory))
			throw new CategoryNotFoundException();
	}

	private void validateEffectedRowUpdated(int effectedRow) {
		if (effectedRow == 0)
			throw new NoTermCategoryHasBeenUpdated();
	}

	private void populateCategoryForUpdate(TermCategory category, Query updateQuery) {
		updateQuery.setParameter("catName", category.getCatName());
		updateQuery.setParameter("allowChildren", category.getAllowChildren());
	}

}
