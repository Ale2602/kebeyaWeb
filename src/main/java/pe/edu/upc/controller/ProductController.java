package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Product;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.IProductService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductService pService;

	@Autowired
	private ICategoryService ctService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Product product = pService.listId(id);
		if (product == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/listProduct";
		}
		model.put("product", product);
		model.put("titulo", "Detalle del Producto: " + product.getNameProduct());
		return "/ver";
	}

	@RequestMapping("/")
	public String irProduct(Map<String, Object> model) {
		model.put("listaProducts", pService.list());
		return "listProduct";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaCategorys", ctService.list());
		model.addAttribute("product", new Product());
		return "product";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Product objProduct, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCategorys", ctService.list());
			return "product";
		} else {
			if (!foto.isEmpty()) {
				if (objProduct.getIdProduct() > 0 && objProduct.getFoto() != null
						&& objProduct.getFoto().length() > 0) {
					uploadFileService.delete(objProduct.getFoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objProduct.setFoto(uniqueFilename);
			}
			boolean flag = pService.insert(objProduct);
			if (flag) {
				return "redirect:/product/listar";
			} else {
				model.addAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/product/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Product objProduct, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/product/listar";
		} else {
			boolean flag = pService.update(objProduct);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/product/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/product/listar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Product objProduct = pService.listId(id);
		if (objProduct == null) {
			objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
			return "redirect:/product/listar";
		} else {
			model.addAttribute("listaCategorys", ctService.list());
			model.addAttribute("product", objProduct);
			return "product";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes flash) {
		try {
			Product product = pService.listId(id);
			if (id != null && id > 0) {
				pService.delete(id);
				if (uploadFileService.delete(product.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + product.getFoto() + " eliminada con exito!");
				}
				model.put("listaProducts", pService.list());
			}
		} catch (Exception e) {
			model.put("mensaje", "Se eliminó");
			model.put("listaProducts", pService.list());
		}
		return "listProduct";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaProducts", pService.list());
		return "listProduct";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Product product) {
		pService.listId(product.getIdProduct());
		return "listProduct";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Product product) throws ParseException {
		List<Product> listaProducts;
		product.setCodeProduct(product.getCodeProduct());
		listaProducts = pService.findCodeProduct(product.getCodeProduct());
		model.put("listaProducts", listaProducts);
		return "buscarProduct";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("product", new Product());
		return "buscarProduct";
	}
}
