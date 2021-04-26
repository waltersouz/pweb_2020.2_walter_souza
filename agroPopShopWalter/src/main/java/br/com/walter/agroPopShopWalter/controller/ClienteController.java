package br.com.walter.agroPopShopWalter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.walter.agroPopShopWalter.model.Cliente;
import br.com.walter.agroPopShopWalter.repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repo;

	@GetMapping(value = "/adicionaCliente")
	public ModelAndView telaAdiciona() {
		ModelAndView mv = new ModelAndView("adicionaCliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@PostMapping("/adicionaCliente")
	public String adicionarPessoa(Cliente cliente) {
		repo.save(cliente);
		return "redirect:/cliente/listarClientes";
	}
	
	@GetMapping(value = "/listarClientes")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("listarClientes");
		mv.addObject("clientes", repo.findAll());
		return mv;
	}
	
	@PostMapping(value = "/editar")
	public ModelAndView editar(Cliente pessoa) {
		repo.save(pessoa);
		return new ModelAndView("redirect:/cliente/listarClientes");
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarTela(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("editarCliente");
		mav.addObject("cliente", repo.findById(id));
		return mav;
	}
	
	@GetMapping(value = "/remover/{id}")
	public String remover(@PathVariable Long id) {
		repo.delete(repo.getOne(id));
		return "redirect:/cliente/listarClientes";
	}
}
