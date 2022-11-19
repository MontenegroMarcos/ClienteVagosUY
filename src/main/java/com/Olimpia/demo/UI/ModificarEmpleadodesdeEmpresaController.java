package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModificarEmpleadodesdeEmpresaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField campoCorreo;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoPsw;

    @FXML
    private TextField campoSaldo;


    void init(String nombre,String correo,String psw , String saldo){
        this.campoNombre.setText(nombre);
        this.campoCorreo.setText(correo);
        this.campoPsw.setText(psw);
        this.campoSaldo.setText(saldo);
    }

}
