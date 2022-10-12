package com.Olimpia.demo.UI;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuariosFinalesController implements Initializable {

    private InicioController controllerInicio;
    private Stage estage;

    @FXML
    private Text textoUsuario;
    @FXML
    private Button btnBuscar;

    private String texto;

    public void init(String text, Stage stage, InicioController inicioController) {
        this.textoUsuario.setText(text);
        this.controllerInicio = inicioController;
        this.estage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*System.out.println(texto);
        if(texto!=null){
            this.textoUsuario.setText(texto);
        }*/
    }
}
