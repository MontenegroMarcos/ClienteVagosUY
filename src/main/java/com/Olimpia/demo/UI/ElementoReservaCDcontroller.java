package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividadRealizada;
import com.Olimpia.demo.modelo.ModeloEmpleado;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
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

    public void setData(ModeloActividadRealizada reserva, ModeloEmpleado empleado) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        this.correoCliente.setText(empleado.getEmail());
        this.empresaCliente.setText(empleado.getEmpresa().getNombre());
        this.fechaActividad.setText(format1.format(reserva.getFecha()));
        this.horainicioActividad.setText(reserva.getHorarios().getKey().getHoraInicio());
        this.horafinalActividad.setText(reserva.getHorarios().getKey().getHoraFin());
        this.nombreActividad.setText(reserva.getActividad().getKey().getNombre());
        this.nombreCliente.setText(empleado.getNombre());
        this.precioActividad.setText(String.valueOf(reserva.getActividad().getPrecio()));
    }
}
