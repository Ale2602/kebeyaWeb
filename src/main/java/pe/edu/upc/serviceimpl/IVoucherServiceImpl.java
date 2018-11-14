package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IVoucherDAO;
import pe.edu.upc.entity.Voucher;
import pe.edu.upc.service.IVoucherService;

@Service
public class IVoucherServiceImpl implements IVoucherService {
	@Autowired
	private IVoucherDAO dVoucher;

	@Override
	@Transactional
	public boolean insert(Voucher voucher) {
		Voucher objVoucher = dVoucher.save(voucher);
		if (objVoucher == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean update(Voucher voucher) {
		boolean flag = false;
		try {
			dVoucher.save(voucher);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idVoucher) {
		dVoucher.delete(idVoucher);

	}

	@Override
	public Voucher listId(int idVoucher) {
		return dVoucher.findOne(idVoucher);
	}

	@Override
	public List<Voucher> list() {
		return dVoucher.findAll();
	}

	@Override
	public List<Voucher> detailTotal(int idClient) {
		return dVoucher.detailTotal(idClient);
	}
}
