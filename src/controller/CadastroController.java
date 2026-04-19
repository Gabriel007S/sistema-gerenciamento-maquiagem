package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Categoria;
import model.Produto;
import service.EstoqueService;

public class CadastroController {
	
	@FXML
	private TextField campoNome;
	
	@FXML
	private TextField campoPrecoCompra;
	
	@FXML
	private TextField campoPrecoVenda;
	
	@FXML
	private TextField campoQuantidade;
	
	@FXML
	private ComboBox<Categoria> comboCategoria;
	
	private EstoqueService service;
	
	private MovimentacaoController movimentacaoController;
	
	private Categoria maquiagem =
			new Categoria(1, "Maquiagem");
	private Categoria livro = new Categoria(2, "Livro");

	
	private int contadorId = 1;
	
	public void setService(EstoqueService service) {
		this.service = service;
	}
	
	@FXML
	public void initialize() {
		
		campoPrecoCompra.textProperty().addListener((obs, oldValue, newValue) -> {
			campoPrecoCompra.setText(formatarMoeda(newValue));
			campoPrecoCompra.positionCaret(campoPrecoCompra.getText().length());
		});
		
		campoPrecoVenda.textProperty().addListener((obs, oldValue, newValue) ->{
			campoPrecoVenda.setText(formatarMoeda(newValue));
			campoPrecoVenda.positionCaret(campoPrecoVenda.getText().length());
		});

		campoQuantidade.textProperty().addListener((obs, oldValue, newValue) ->{
			if (!newValue.matches("\\d*")) {
				campoQuantidade.setText(newValue.replaceAll("[^\\d]", ""));

			}
		});
		
		comboCategoria.getItems().addAll(
				maquiagem, livro, new Categoria(3, "Perfume"), new Categoria(4, "Acessório")
				);
		comboCategoria.getSelectionModel().selectFirst();
	}
	
	public void setMovimentacaoController(MovimentacaoController controller) {
		this.movimentacaoController = controller;
	}
	
	@FXML
	private void handleCadastrar() {
		
		if (campoNome.getText().trim().isEmpty() ||
			campoPrecoCompra.getText().isEmpty() ||
			campoPrecoVenda.getText().isEmpty() ||
			campoQuantidade.getText().isEmpty()) {
			
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Preencha todos os campos.");
			alert.show();
			return;
		}
		try {
			String nome = campoNome.getText();
			
			float precoCompra = Float.parseFloat(campoPrecoCompra.getText().replace(",", "."));
			
			float precoVenda = Float.parseFloat(campoPrecoVenda.getText().replace(",", "."));
			
			int quantidade = Integer.parseInt(campoQuantidade.getText());
			
			Produto produto = new Produto(
					contadorId++,
					nome,
					comboCategoria.getValue(),
					precoCompra,
					precoVenda,
					quantidade);
			
			service.cadastrarProduto(produto);
			
			if (movimentacaoController != null) {
				movimentacaoController.atualizarLista();
			}
			
			limparCampos();
			
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Erro ao cadastrar");
			alert.show();
		}
		
	}
	private void limparCampos() {
		campoNome.clear();
		campoPrecoCompra.clear();
		campoPrecoVenda.clear();
		campoQuantidade.clear();
		
	}
	
	private String formatarMoeda(String valor) {
		
		valor = valor.replaceAll("[^\\d]", "");
		
		if(valor.isEmpty()) {
			return "";
		}
		
		double numero = Double.parseDouble(valor) / 100;
		
		return String.format("%.2f", numero).replace(".", ",");
	}	

}
