package com.Olimpia.demo.UI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import kong.unirest.Unirest;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CentroDeportivoController implements Initializable {

    @FXML
    private Button AgregarActividad;

    @FXML
    private TextFlow DiasyHorarios;

    @FXML
    private Button aniadirHorarioAgregar;

    @FXML
    private Text balancefinal;

    @FXML
    private Button btnCheckInCancelar;

    @FXML
    private Button btnCheckin;

    @FXML
    private Button cancelarActividadClear;

    @FXML
    private TextField checkinCorreo;

    @FXML
    private ComboBox<String> checkinAcrividades;

    @FXML
    private ComboBox<?> comboboxCategoriasAgregar;

    @FXML
    private ComboBox<?> comboxDiasAgregar;

    @FXML
    private TextField cuposAgregar;

    @FXML
    private TextField horariosAgregar;

    @FXML
    private Button modificarGestion;

    @FXML
    private TextField nombreActividadAgregar;

    @FXML
    private TextField nombreCheckin;

    @FXML
    private TextField precioAgregar;

    @FXML
    private TableColumn<?, ?> preciotablaGestion;

    @FXML
    private TableView<?> tablaGestionActividades;

    @FXML
    private TableColumn<?, ?> tablaHorariosGestionar;

    @FXML
    private TableColumn<?, ?> tablanombreGestion;

    @FXML
    private Text textoUsuario;

    @FXML
    private ComboBox<Integer> horarioInicio;

    @FXML
    private DatePicker selectorFecha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private List<List> obtenerActividades() {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/Todas").asString().getBody();
        List<List> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    public List<List> obtenerActividadesPorNombre(String nombre) {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/" + nombre).asString().getBody();
        List<List> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }


    //Check-In

    @FXML
    private void Checkin(){

        String nombreUser = nombreCheckin.getText();
        String actividad = checkinAcrividades.getValue();
        DayOfWeek fecha = selectorFecha.getValue().getDayOfWeek();
        int horaInicio = horarioInicio.getValue();


        if(confirmarReserva(nombreUser,actividad)){

        } else {
            //Manda Error
        }
    }
    @FXML
    private void limpiarCheckin(){

        this.nombreCheckin.setText(null);



    }
    private boolean confirmarReserva(String nombre, String Actividad){

        return false;
    }

    //Actividades

    //Modificar ACTividades

    //Balance

    @FXML
    void obtenerBalance(){

        String Currency = "$";
    }



}
