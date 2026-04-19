package service;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.Venda;

public class EstoqueService {

	private  List<Produto> produtos;
	private List<Venda> vendas;
	
	public EstoqueService() {
		this.produtos = new ArrayList<>();
		this.vendas = new ArrayList<>();
	}
	
	public Produto buscarProdutoPorId(int id) {
		for (Produto p : produtos) {
		 if (p.getId() == id) {
			 return p;
		  }
		}
		 throw new IllegalArgumentException("Produto não encontrado.");
	}
	public void cadastrarProduto (Produto produto) {
		for (Produto p : produtos){
			if (p.getId() == produto.getId()) {
			    throw new IllegalArgumentException("Produto já cadastrado.");
		    }
	   }
	   produtos.add(produto);
	}
	public void registrarCompra(int id, int qtd) {
		Produto produto = buscarProdutoPorId(id);
		produto.aumentarQuantidade(qtd);
	}
	
	public void registrarVenda(int id, int qtd) {
		Produto produto = buscarProdutoPorId(id);
		
		produto.diminuirQuantidade(qtd);
		
		Venda venda = new Venda(vendas.size() + 1, produto, qtd);
		vendas.add(venda);
	}
	public List<Produto> listarProdutos() {
		return produtos;
	}
	
	public List<Venda> listarVendas(){
		return vendas;
	}
	public float calcularLucroTotal() {
		float total = 0;
		for (Venda v : vendas) {
			total += v.calcularLucro();
		}
		return total;
	}
	public int getTotalVendas() {
		return vendas.size();
	}
	public int getTotalUnidadesVendidas() {
		int total = 0; 
		for (Venda v : vendas) {
			total += v.getQuantidade();
		}
		return total;
	}
}
