package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Product;

@Repository
public interface IProductDAO extends JpaRepository<Product, Integer> {
	@Query("from Product p where p.codeProduct like %:codeProduct%")
	List<Product> findCodeProduct(@Param("codeProduct") String codeProduct);

	@Query("from Product p where p.category.nameCategory like %:nameCategory%")
	List<Product> findCategory(@Param("nameCategory") String nameCategory);
}
