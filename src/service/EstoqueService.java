package service;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class EstoqueService {

	private  List<Produto> produtos;
	
	public EstoqueService() {
		this.produtos = new ArrayList<>();
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
	}
	public List<Produto> listarProdutos() {
		return produtos;
	}
	
}
