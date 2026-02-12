package interfacer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainApp extends Application {

    private ObservableList<String> produtos = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Gerenciamento de Maquiagem");

        TextField campoProduto = new TextField();
        campoProduto.setPromptText("Nome do produto");

        Button adicionar = new Button("Adicionar");

        ListView<String> lista = new ListView<>(produtos);

        adicionar.setOnAction(e -> {
            String nome = campoProduto.getText();
            if (!nome.isEmpty()) {
                produtos.add(nome);
                campoProduto.clear();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titulo, campoProduto, adicionar, lista);

        Scene scene = new Scene(layout, 400, 400);

        stage.setTitle("Sistema de Gerenciamento");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
