package br.com.walter.agroPopShopWalter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.walter.agroPopShopWalter.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findAllByOrderByNomeAsc();
	
	List<Produto> findAllByNomeContainingIgnoreCaseOrderByNome(String nome);
}
