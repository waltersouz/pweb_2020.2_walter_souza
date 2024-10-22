package br.com.walter.agroPopShopWalter.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		produto.setDataCadastro(LocalDateTime.now());
		repo.save(produto);
		return "redirect:/produto/listarProdutos";
	}
	
	@GetMapping(value = "/listarProdutos")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("listarProdutos");
		mv.addObject("produto", new Produto());
		mv.addObject("produtos", repo.findAll());
		return mv;
	}
	
	@PostMapping(value = "/listarProdutos")
	public ModelAndView lista(Produto p) {
		ModelAndView mv = new ModelAndView("listarProdutos");
		mv.addObject("produto", p);
		if(p.getNome().isEmpty() || p.getNome().isEmpty()) {
			mv.addObject("produtos", repo.findAll());
		} else {
			mv.addObject("produtos", repo.findAllByNomeContainingIgnoreCaseOrderByNome(p.getNome()));
		}
		return mv;
	}
	
	@GetMapping(value = "/freteMarte")
	public ModelAndView listaPorFrete() {
		ModelAndView mv = new ModelAndView("freteMarte");
		List<Produto> produtos = new ArrayList<>();
		produtos = repo.findAll();
		Collections.sort(produtos, Collections.reverseOrder());
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@GetMapping(value = "/desconto")
	public ModelAndView desconto() {
		ModelAndView mv = new ModelAndView("desconto");
		List<Produto> produtos = new ArrayList<>();
		produtos = repo.findTop4ByOrderByDataCadastroDesc();
		Collections.sort(produtos, new Comparator<Produto>() {
			public int compare(Produto o1, Produto o2) {
				Double x1 = o1.desconto();
				Double x2 = o2.desconto();				
				return x1.compareTo(x2);
			};
		});
		Collections.reverse(produtos);
		mv.addObject("produtos", produtos);
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
