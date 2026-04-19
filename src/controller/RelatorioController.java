package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.EstoqueService;

public class RelatorioController {
	
	@FXML
	private Label labelLucroTotal;
	
	@FXML
	private Label labelTotalVendas;
	
	@FXML
	private Label labelUnidadesVendidas;
	
	private EstoqueService service;
	
	public void setService(EstoqueService service) {
		this.service = service;
		atualizar();
	}
	
	
	public void atualizar() {
		
		float lucro = service.calcularLucroTotal();
		int total = service.getTotalVendas();
		int unidades = service.getTotalUnidadesVendidas();
		
		labelLucroTotal.setText(String.format("%.2f", lucro));
		labelTotalVendas.setText(String.valueOf(total));
		labelUnidadesVendidas.setText(String.valueOf(unidades));
	}
	@FXML
	private void handleAtualizar() {
		atualizar();
	}

}
