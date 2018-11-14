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

import pe.edu.upc.entity.Client;
import pe.edu.upc.service.IClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private IClientService cService;

	@RequestMapping("/")
	public String irClient(Map<String, Object> model) {
		model.put("listaClients", cService.list());
		return "listClient";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("client", new Client());
		return "client";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Client objClient, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "client";
		} else {
			boolean flag = cService.insert(objClient);
			if (flag) {
				return "redirect:/client/listar";
			} else {
				model.addAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/client/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Client objClient, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/client/listar";
		} else {
			boolean flag = cService.update(objClient);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/client/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
				return "redirect:/client/listar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Client objClient = cService.listId(id);
		if (objClient == null) {
			objRedir.addFlashAttribute("mensaje", "NO ES LA LLAMA QUE BUSCAS");
			return "redirect:/client/listar";
		} else {
			model.addAttribute("client", objClient);
			return "client";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.delete(id);
				model.put("listaClients", cService.list());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un Cliente asignado");
			model.put("listaClients", cService.list());
		}
		return "listClient";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClients", cService.list());
		return "listClient";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Client client) {
		cService.listId(client.getIdClient());
		return "listClient";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Client client) throws ParseException {
		List<Client> listaClients;
		client.setDniClient(client.getDniClient());
		listaClients = cService.findDniClient(client.getDniClient());
		model.put("listaClients", listaClients);
		return "buscarClient";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("client", new Client());
		return "buscarClient";
	}
}
