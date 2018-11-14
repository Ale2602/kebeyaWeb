package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Category;

@Repository
public interface ICategoryDAO extends JpaRepository<Category, Integer> {
	@Query("from Category ct where ct.codeCategory like %:codeCategory%")
	List<Category> findCodeCategory(@Param("codeCategory") String codeCategory);

	@Query("from Category ct where ct.nameCategory like %:nameCategory%")
	List<Category> findNameCategory(@Param("nameCategory") String nameCategory);
}
