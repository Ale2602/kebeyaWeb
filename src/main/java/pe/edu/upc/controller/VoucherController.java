package pe.edu.upc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Client;
import pe.edu.upc.entity.Product;
import pe.edu.upc.entity.Voucher;
import pe.edu.upc.service.IClientService;
import pe.edu.upc.service.IProductService;
import pe.edu.upc.service.IVoucherService;
/*
@Controller
@SessionAttributes("voucher")
@RequestMapping("/voucher")
public class VoucherController {
	@Autowired
	private IVoucherService vService;
	@Autowired
	private IClientService cService;
	@Autowired
	private IProductService pService;
	
	@GetMapping(value = "/voucher/{id}")
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
}*/
