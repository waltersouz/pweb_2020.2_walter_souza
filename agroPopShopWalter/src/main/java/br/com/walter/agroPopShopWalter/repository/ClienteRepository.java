package br.com.walter.agroPopShopWalter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.walter.agroPopShopWalter.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
