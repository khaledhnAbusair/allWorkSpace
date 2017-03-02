package repositories;

import java.util.Collection;

import entity.TermCategory;

public interface TermCategoryRepositorey {

	public void editCategory(TermCategory category);

	public void addCategory(TermCategory category);

	public void deleteCategory(TermCategory category);

	public Collection<TermCategory> loadCategories();

}
