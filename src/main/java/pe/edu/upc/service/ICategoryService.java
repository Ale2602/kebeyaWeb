package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Category;

public interface ICategoryService {
	public boolean insert(Category category);

	public boolean update(Category category);

	public void delete(int idCategory);

	public Category listId(int idCategory);

	List<Category> list();

	List<Category> findCodeCategory(String codeCategory);

	List<Category> findNameCategory(String nameCategory);
}
