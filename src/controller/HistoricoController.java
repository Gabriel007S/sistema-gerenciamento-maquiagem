package controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Venda;
import service.EstoqueService;

public class HistoricoController {

    @FXML
    private TextField campoFiltroProduto;

    @FXML
    private TableView<Venda> tabelaVendas;

    @FXML
    private TableColumn<Venda, Integer> colId;

    @FXML
    private TableColumn<Venda, String> colProduto;

    @FXML
    private TableColumn<Venda, Integer> colQtd;

    @FXML
    private TableColumn<Venda, String> colTotal;

    @FXML
    private TableColumn<Venda, String> colData;

    @FXML
    private TableColumn<Venda, String> colLucro;
    
    @FXML
    private TableColumn<Venda, String> colCategoria;

    private EstoqueService service;

    public void setService(EstoqueService service) {
        this.service = service;
        atualizarListaVendas();
    }

    @FXML
    public void initialize() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        colId.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getId()).asObject());

        colProduto.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getProduto().getNome()));
        
        colCategoria.setCellValueFactory(c -> 
                new SimpleStringProperty(
                		c.getValue()
                		 .getProduto()
                		 .getCategoria()
                		 .getNome()
                		 ));

        colQtd.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        c.getValue().getQuantidade()).asObject());

        colTotal.setCellValueFactory(c ->
                new SimpleStringProperty(
                        String.format("%.2f",
                                c.getValue().getTotalVenda())));

        colData.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getData().format(formatter)));

        colLucro.setCellValueFactory(c ->
                new SimpleStringProperty(
                        String.format("%.2f",
                                c.getValue().calcularLucro())));

        campoFiltroProduto.textProperty().addListener(
                (obs, oldVal, newVal) -> atualizarListaVendas());
    }

    public void atualizarListaVendas() {

        if (service == null) return;

        List<Venda> lista =
                new ArrayList<>(service.listarVendas());

        String filtro = campoFiltroProduto.getText();

        if (filtro != null && !filtro.isEmpty()) {
            lista.removeIf(v ->
                    !v.getProduto().getNome()
                            .toLowerCase()
                            .contains(filtro.toLowerCase()));
        }

        lista.sort((v1, v2) ->
                v2.getData().compareTo(v1.getData()));

        tabelaVendas.getItems().setAll(lista);
    }
}