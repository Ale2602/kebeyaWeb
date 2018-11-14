package pe.edu.upc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Voucher;

@Repository
public interface IVoucherDAO extends JpaRepository<Voucher, Integer> {
	@Query("select v from Voucher v join fetch v.client p join fetch v.voucherdetail vd join fetch vd.product where v.id=?1")
	List<Voucher> detailTotal(int idClient);
}
