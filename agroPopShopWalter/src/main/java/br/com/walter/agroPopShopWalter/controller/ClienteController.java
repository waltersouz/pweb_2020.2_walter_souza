package br.com.walter.agroPopShopWalter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.walter.agroPopShopWalter.model.Cliente;
import br.com.walter.agroPopShopWalter.model.Dependente;
import br.com.walter.agroPopShopWalter.repository.ClienteRepository;
import br.com.walter.agroPopShopWalter.repository.DependenteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private DependenteRepository dependenteRepository;

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
	
	@GetMapping(value = "/listarDependentes")
	public ModelAndView listaDependentes() {
		ModelAndView mv = new ModelAndView("listarDependentes");
		mv.addObject("dependentes", dependenteRepository.findAll());
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
	
	@GetMapping(value = "/adicionaDependente")
	public ModelAndView adicionaDependente() {
		ModelAndView mv = new ModelAndView("adicionaDependente");
		mv.addObject("clientes", repo.findAll());
		mv.addObject("dependente", new Dependente());
		return mv;		
	}
	
	@PostMapping(value = "/adicionaDependente")
	public String adicionaDependente(Dependente dependente) {
		dependenteRepository.save(dependente);
		return "redirect:/cliente/listarDependentes";		
	}
	
	@PostMapping(value = "/editarDependente")
	public ModelAndView editarDependentes(Dependente dependente) {
		dependenteRepository.save(dependente);
		return new ModelAndView("redirect:/cliente/listarDependentes");
	}
	
	@GetMapping("/editarDependente/{id}")
	public ModelAndView editarTelaDependente(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("editarDependente");
		mav.addObject("dependente", dependenteRepository.findById(id));
		return mav;
	}
	
	@GetMapping(value = "/removerDependente/{id}")
	public String removerDependente(@PathVariable Long id) {
		dependenteRepository.delete(dependenteRepository.getOne(id));
		return "redirect:/cliente/listarDependentes";
	}
}
