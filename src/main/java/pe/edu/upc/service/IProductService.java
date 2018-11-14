package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Product;

public interface IProductService {
	public boolean insert(Product product);

	public boolean update(Product product);

	public void delete(int idProduct);

	public Product listId(int idProduct);

	List<Product> list();

	List<Product> findCodeProduct(String codeProduct);

	List<Product> findCategory(String nameCategory);
}
