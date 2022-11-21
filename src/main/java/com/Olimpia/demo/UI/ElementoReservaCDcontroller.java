package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividadRealizada;
import com.Olimpia.demo.modelo.ModeloEmpleado;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.text.SimpleDateFormat;
import java.util.List;

public class ElementoReservaCDcontroller {

    CentroDeportivoController controller;

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

    private ModeloActividadRealizada reserva;
    private ModeloEmpleado empleadoReserva;

    public void setData(ModeloActividadRealizada reserva, ModeloEmpleado empleado) {
        this.reserva = reserva;
        this.empleadoReserva = empleado;
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

    public void aceptarReserva() {
        ModeloActividadRealizada reserva = new ModeloActividadRealizada(this.reserva.getFecha(), this.reserva.getActividad(), this.reserva.getHorarios(), this.empleadoReserva, this.reserva.getCupos(), null);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(reserva);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/reserva/checkIn")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(jsonString)
                    .asJson();
            this.controller.actualizar();
            this.controller.actualizarBalance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setcontrol(CentroDeportivoController centroDeportivoController) {
        this.controller = centroDeportivoController;
    }
}
