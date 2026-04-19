package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Produto;
import service.EstoqueService;

public class MovimentacaoController {

    @FXML
    private ListView<Produto> listaProdutos;
    
    @FXML
    private TextField campoQtdMovimento;
    
    @FXML
    public void initialize() {
    }

    private EstoqueService service;
    
    private HistoricoController historicoController;
    
    private RelatorioController relatorioController;

    public void setService(EstoqueService service) {
        this.service = service;
        atualizarLista();
    }
    
   public void setHistoricoController(HistoricoController controller) {
	   this.historicoController = controller;
   }
   
   public void setRelatorioController(RelatorioController controller) {
	   this.relatorioController = controller;
   }

    public void atualizarLista() {
    	if (service == null || listaProdutos == null) return;
        listaProdutos.getItems().setAll(service.listarProdutos());
    }
    @FXML
    private void handleVenda() {

        try {
            Produto selecionado =
                    listaProdutos.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                return;
            }

            int qtd =
                    Integer.parseInt(campoQtdMovimento.getText());

            service.registrarVenda(selecionado.getId(), qtd);

            atualizarLista();
            
            if (historicoController != null) {
            	historicoController.atualizarListaVendas();
            }
            
            if (relatorioController != null) {
            	relatorioController.atualizar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleCompra() {

        try {
            Produto selecionado =
                    listaProdutos.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                return;
            }

            int qtd =
                    Integer.parseInt(campoQtdMovimento.getText());

            service.registrarCompra(selecionado.getId(), qtd);

            atualizarLista();
            
            if (historicoController != null) {
            	historicoController.atualizarListaVendas();
            }
            
            if (relatorioController != null) {
            	relatorioController.atualizar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}