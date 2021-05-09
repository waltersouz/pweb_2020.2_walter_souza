package br.com.walter.agroPopShopWalter.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.walter.agroPopShopWalter.model.Cliente;
import br.com.walter.agroPopShopWalter.model.Pedido;
import br.com.walter.agroPopShopWalter.repository.ClienteRepository;
import br.com.walter.agroPopShopWalter.repository.PedidoRepository;
import br.com.walter.agroPopShopWalter.repository.ProdutoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@GetMapping(value = "/adicionaPedido")
	public ModelAndView telaAdiciona() {
		ModelAndView mv = new ModelAndView("adicionaPedido");
		mv.addObject("pedido", new Pedido());
		mv.addObject("produtos", produtoRepo.findAllByOrderByNomeAsc());
		mv.addObject("clientes", clienteRepo.findAllByOrderByNomeCompletoAsc());
		return mv;
	}
	
	@PostMapping(value = "/adicionaPedido")
	public String adicionaPedido(Pedido pedido) {
		pedido.setValorUnitario(produtoRepo.getOne(pedido.getProduto().getCodigo()).getPreco());
		pedido.setDataCadastro(LocalDate.now());
		pedidoRepo.save(pedido);
		return "redirect:/pedido/listarPedidos";
	}
	
	@GetMapping(value = "/listarPedidos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("listarPedidos");
		mv.addObject("pedidos", pedidoRepo.findAll());
		return mv;
	}
}
