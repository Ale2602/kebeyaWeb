package pe.edu.upc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Client;

@Repository
public interface IClientDAO extends JpaRepository<Client, Integer> {
	@Query("from Client c where c.dniClient like %:dniClient%")
	List<Client> findDniClient(@Param("dniClient") String dniClient);
	
	@Query("from Client c where c.nameClient like %:nameClient%")
	List<Client> findNameClient(@Param("nameClient") String nameClient);

	@Query("from Client c where c.lastClient like %:lastClient%")
	List<Client> findLastClient(@Param("lastClient") String lastClient);
}
