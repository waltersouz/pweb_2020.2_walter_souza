package br.com.walter.cadpessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.walter.cadpessoas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
