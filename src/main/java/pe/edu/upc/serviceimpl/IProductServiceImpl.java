package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IProductDAO;
import pe.edu.upc.entity.Product;
import pe.edu.upc.service.IProductService;

@Service
public class IProductServiceImpl implements IProductService {
	@Autowired
	private IProductDAO dProduct;

	@Override
	@Transactional
	public boolean insert(Product product) {
		Product objProduct = dProduct.save(product);
		if (objProduct == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean update(Product product) {
		boolean flag = false;
		try {
			dProduct.save(product);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idProduct) {
		dProduct.delete(idProduct);
	}

	@Override
	public Product listId(int idProduct) {
		return dProduct.findOne(idProduct);
	}

	@Override
	public List<Product> list() {
		return dProduct.findAll();
	}

	@Override
	public List<Product> findCodeProduct(String codeProduct) {
		return dProduct.findCodeProduct(codeProduct);
	}

	@Override
	public List<Product> findCategory(String nameCategory) {
		return dProduct.findCategory(nameCategory);
	}
}
