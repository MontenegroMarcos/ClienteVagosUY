package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class ElementoReservaCDcontroller {

    @FXML
    private Button btnAceptar;
    @FXML
    private Label correoCliente;

    @FXML
    private Label empresaCliente;

    @FXML
    private Label fechaActividad;

    @FXML
    private Label horafinalActividad;

    @FXML
    private Label horainicioActividad;

    @FXML
    private Label nombreActividad;

    @FXML
    private Label nombreCliente;

    @FXML
    private Label precioActividad;

    public void setData(List list, String text, CentroDeportivoController centroDeportivoController) {
    }
}
