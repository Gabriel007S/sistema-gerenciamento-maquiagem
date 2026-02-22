package model;

public class Produto {
	private int id;
	private String nome;
	private float precoCompra;
	private float precoVenda;
	private int quantidade;
	
	public Produto(int id, String nome, float precoCompra, float precoVenda, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void diminuirQuantidade (int qtd) {
		if (qtd <= 0) {
			throw new IllegalArgumentException("Quantidade dever ser maior que zero.");
		}
		if (qtd > this.quantidade) {
			throw new IllegalStateException("Quantidade insuficiente.");
		}
		this.quantidade -= qtd;
	}
	public void aumentarQuantidade (int qtd) {
		if (qtd <= 0) {
			throw new IllegalArgumentException("Quantidade dever ser maior que zero.");
		}
		this.quantidade += qtd;
	}
	
	
}
