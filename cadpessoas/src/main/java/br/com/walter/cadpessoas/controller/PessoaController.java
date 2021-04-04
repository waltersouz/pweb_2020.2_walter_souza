package br.com.walter.cadpessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.walter.cadpessoas.model.Pessoa;
import br.com.walter.cadpessoas.repository.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepo;

	@GetMapping("/")
	public String inicio() {
		return "index";
	}

	@GetMapping("/listarPessoas")
	public ModelAndView listarPessoas() {
		return new ModelAndView("listarPessoas").addObject("pessoas", pessoaRepo.findAll());
	}

	@GetMapping("/adicionarPessoa")
	public ModelAndView adicionarPessoa() {
		ModelAndView mav = new ModelAndView("adicionarPessoas");
		mav.addObject("pessoa", new Pessoa());
		return mav;
	}

	@PostMapping("/adicionarPessoa")
	public String adicionarPessoa(Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/listarPessoas";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarPessoa(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("editarPessoa");
		mav.addObject("pessoa", pessoaRepo.findById(id));
		return mav;
	}

	@PostMapping("/editar")
	public ModelAndView editarPessoa(Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return new ModelAndView("redirect:/listarPessoas");
	}
}
