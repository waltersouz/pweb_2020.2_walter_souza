package br.com.walter.agroPopShopWalter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.walter.agroPopShopWalter.model.Produto;
import br.com.walter.agroPopShopWalter.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repo;
	
	@GetMapping(value = "/adicionaProduto")
	public ModelAndView telaAdiciona() {
		ModelAndView mv = new ModelAndView("adicionaProduto");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	@PostMapping("/adicionaProduto")
	public String adicionarPessoa(Produto produto) {
		repo.save(produto);
		return "redirect:/produto/listarProdutos";
	}
	
	@GetMapping(value = "/listarProdutos")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("listarProdutos");
		mv.addObject("produtos", repo.findAll());
		return mv;
	}
	
	@PostMapping(value = "/editar")
	public ModelAndView editar(Produto produto) {
		repo.save(produto);
		return new ModelAndView("redirect:/produto/listarProdutos");
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarTela(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("editarProduto");
		mav.addObject("produto", repo.findById(id));
		return mav;
	}
	
	@GetMapping(value = "/remover/{id}")
	public String remover(@PathVariable Long id) {
		repo.delete(repo.getOne(id));
		return "redirect:/produto/listarProdutos";
	}

}
