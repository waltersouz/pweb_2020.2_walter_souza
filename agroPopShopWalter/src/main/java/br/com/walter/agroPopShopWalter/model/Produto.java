package br.com.walter.agroPopShopWalter.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto implements Serializable, Comparable<Produto> {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private String marca;
	private int altura;
	private int largura;
	private int profundidade;
	private double peso;
	private double preco;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;

	@OneToMany(mappedBy = "produto", cascade = { CascadeType.REMOVE })
	private List<Pedido> pedidos;

	public int getVolumeProduto() {
		int v = altura * largura * profundidade;
		return v;
	}
	
	public double getFrete() {
		return peso * 1000 * 123456;
	}
	
	public double desconto() {
		return preco * (1 - 0.03018735);
	}

	@Override
	public int compareTo(Produto o) {
		if(getVolumeProduto() > o.getVolumeProduto() ) {
			return 1;
		}
		
		if(getVolumeProduto() < o.getVolumeProduto() ) {
			return -1;
		}
		
		return 0;
	}


}
