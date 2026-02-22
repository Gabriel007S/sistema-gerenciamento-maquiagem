package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Produto;
import service.EstoqueService;

public class MainController {
	
	@FXML
	private TextField campoNome;
	
	@FXML 
	private ListView<String> listaProdutos;
	
	private EstoqueService service = new EstoqueService();
	 
	private ObservableList<String> listaVisual = 
			FXCollections.observableArrayList();
	
	private int contadorId = 1;
	
	@FXML
	public void initialize() {
		listaProdutos.setItems(listaVisual);
	}
	
	@FXML
	private void handleCadastrar() {
		String nome = campoNome.getText();
		if (!nome.isEmpty()) {
			Produto produto = new Produto(
					contadorId++,
					nome,
					30.0f, 
					20.0f,
					0
					);
			service.cadastrarProduto(produto);
			atualizarLista();
			campoNome.clear();
		}
	}
	private void atualizarLista() {
		listaVisual.clear();
		for (Produto p : service.listarProdutos()) {
			listaVisual.add(p.getId() + "-" + p.getNome() +
					" | Estoque: " + p.getQuantidade());
		}
	}

}
