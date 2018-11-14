package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Category;
import pe.edu.upc.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService ctService;

	@RequestMapping("/welcome")
	public String irWelcome() {
		return "welcome";
	}
	
	@RequestMapping("/contacto")
	public String irContacto() {
		return "contacto";
	}

	@RequestMapping("/")
	public String irCategory(Map<String, Object> model) {
		model.put("listaCategorys", ctService.list());
		return "listCategory";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("category", new Category());
		return "category";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Category objCategory, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "category";
		} else {
			boolean flag = ctService.insert(objCategory);
			if (flag) {
				return "redirect:/category/listar";
			} else {
				model.addAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/category/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Category objCategory, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/category/listar";
		} else {
			boolean flag = ctService.update(objCategory);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/category/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/category/listar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Category objCategory = ctService.listId(id);
		if (objCategory == null) {
			objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
			return "redirect:/category/listar";
		} else {
			model.addAttribute("category", objCategory);
			return "category";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				ctService.delete(id);
				model.put("listaCategorys", ctService.list());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Categoria asignada");
			model.put("listaCategorys", ctService.list());

		}
		return "listCategory";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCategorys", ctService.list());
		return "listCategory";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Category category) {
		ctService.listId(category.getIdCategory());
		return "listCategory";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Category category) throws ParseException {
		List<Category> listaCategorys;
		category.setCodeCategory(category.getCodeCategory());
		listaCategorys = ctService.findCodeCategory(category.getCodeCategory());
		model.put("listaCategorys", listaCategorys);
		return "buscarCategory";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("category", new Category());
		return "buscarCategory";
	}
}
