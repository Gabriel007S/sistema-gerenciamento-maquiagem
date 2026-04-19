package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {

	private int id;
	private Produto produto;
	private int quantidade;
	private float precoVendaUnitario;
	private float precoCompraUnitario;
	private LocalDateTime data;
	
	
	
	public Venda(int id, Produto produto, int quantidade) {
		super();
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoVendaUnitario = produto.getPrecoVenda();
		this.precoCompraUnitario = produto.getPrecoCompra();
		this.data = LocalDateTime.now();
	}
	public int getId() {
		return id;
	}
	public float calcularLucro() {
		return (precoVendaUnitario - precoCompraUnitario) * quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return "Venda #" + id + 
				" | Produto: " + produto.getNome() +
				" | Qtd: " + quantidade + 
				" | Total: " +String.format("%.2f", getTotalVenda()) +
				" | Data: " + data.format(formatter) +
				" | Lucro: " + String.format("%.2f",calcularLucro());
	}
	public float getTotalVenda() {
		return precoVendaUnitario * quantidade;
	}	
	
}
