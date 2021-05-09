package br.com.walter.agroPopShopWalter.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String formaPagamento;

	private String numeroCartao;

	private int quantidade;

	private double valorUnitario;

	private LocalDate dataCadastro;

	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	public double getTotal() {
		return quantidade * valorUnitario;
	}

	public double getImposto() {
		return getTotal() * 1.225;
	}
}
