package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class HorariosController {
    @FXML
    private Label diaActividad;
    @FXML
    private Label horaInicio;
    @FXML
    private Label horaFin;
    @FXML
    private Label cupos;

    public void setData(List horas) {
        this.diaActividad.setText(String.valueOf(horas.get(0)));
        this.horaInicio.setText(String.valueOf(horas.get(1)));
        this.horaFin.setText(String.valueOf(horas.get(2)));
        this.cupos.setText(String.valueOf(horas.get(3)));
    }


}
