package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Voucher;

public interface IVoucherService {
	public boolean insert(Voucher voucher);

	public boolean update(Voucher voucher);

	public void delete(int idVoucher);

	public Voucher listId(int idVoucher);

	List<Voucher> list();

	List<Voucher> detailTotal(int idClient);
}
