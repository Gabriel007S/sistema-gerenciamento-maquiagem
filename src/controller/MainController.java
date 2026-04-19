package controller;

import javafx.fxml.FXML;
import service.EstoqueService;

public class MainController {

	@FXML
	private CadastroController cadastroViewController;

	@FXML
	private MovimentacaoController movimentacaoViewController;

	@FXML
	private HistoricoController historicoViewController;

	@FXML
	private RelatorioController relatorioViewController;

	private EstoqueService service = new EstoqueService();

	@FXML
	public void initialize() {

		javafx.application.Platform.runLater(() -> {

			cadastroViewController.setService(service);
			movimentacaoViewController.setService(service);
			historicoViewController.setService(service);
			relatorioViewController.setService(service);

			cadastroViewController.setMovimentacaoController(movimentacaoViewController);

			movimentacaoViewController.setHistoricoController(historicoViewController);
			movimentacaoViewController.setRelatorioController(relatorioViewController);

			movimentacaoViewController.atualizarLista();
		});
	}
}