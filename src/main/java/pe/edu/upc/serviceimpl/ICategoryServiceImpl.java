package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ICategoryDAO;
import pe.edu.upc.entity.Category;
import pe.edu.upc.service.ICategoryService;

@Service
public class ICategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryDAO dCategory;

	@Override
	@Transactional
	public boolean insert(Category category) {
		Category objCategory = dCategory.save(category);
		if (objCategory == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean update(Category category) {
		boolean flag = false;
		try {
			dCategory.save(category);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idCategory) {
		dCategory.delete(idCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public Category listId(int idCategory) {
		return dCategory.findOne(idCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> list() {
		return dCategory.findAll();
	}

	@Override
	public List<Category> findCodeCategory(String codeCategory) {
		return dCategory.findCodeCategory(codeCategory);
	}

	@Override
	public List<Category> findNameCategory(String nameCategory) {
		return dCategory.findNameCategory(nameCategory);
	}

}
