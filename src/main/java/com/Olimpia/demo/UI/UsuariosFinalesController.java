package com.Olimpia.demo.UI;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UsuariosFinalesController {

    private InicioController controllerInicio;
    private Stage estage;

    @FXML
    private Text textoUsuario;
    @FXML
    private Button btnBuscar;

    public void init(String text, Stage stage, InicioController inicioController) {
        textoUsuario.setText(text);
        this.controllerInicio = inicioController;
        this.estage = stage;
    }
}
