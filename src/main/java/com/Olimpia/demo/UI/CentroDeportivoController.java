package com.Olimpia.demo.UI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import kong.unirest.Unirest;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
    private ComboBox<String> checkinActividades;

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
    private ComboBox<String> horarioInicio;

    @FXML
    private DatePicker selectorFecha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void init(String emailCD){
        this.textoUsuario.setText(emailCD);
        this.checkinActividades.setItems(obtenerActividadesdeCD());
    }

    private ObservableList<String> obtenerActividadesdeCD() {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/centro/email/"+this.textoUsuario.getText()).asString().getBody();
        List<String> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(actividad);
        return retorno;
    }

    public void seleccionActividad(){
        if(this.selectorFecha.getValue()!=null) {
            this.horarioInicio.getItems().clear();
            this.horarioInicio.setItems(obtenerHorasInicio(this.checkinActividades.getValue(),diaSemanaDate()));
        }
    }

    private ObservableList<String> obtenerHorasInicio(String actividad,String dia) {
        ObjectMapper mapper = new ObjectMapper();
        String strHoras = Unirest.get("http://localhost:8080/vagouy/Actividades/horaInicio/"+actividad+"/"+this.textoUsuario.getText()+"/"+dia).asString().getBody();
        List<String> horas = null;
        try {
            horas = mapper.readValue(strHoras, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(horas);
        return retorno;
    }

    public void seleccionarDia(){
        if(this.checkinActividades!=null){
            this.horarioInicio.getItems().clear();
            this.horarioInicio.setItems(obtenerHorasInicio(this.checkinActividades.getValue(),diaSemanaDate()));
        }
    }

    private String diaSemanaDate(){
        LocalDate localDate = this.selectorFecha.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);
        String dia="";
        switch(diaSemana){
            case 1:
                dia="Domingo";
                break;
            case 2:
                dia="Lunes";
                break;
            case 3:
                dia="Martes";
                break;
            case 4:
                dia="Miercoles";
                break;
            case 5:
                dia="Jueves";
                break;
            case 6:
                dia="Viernes";
                break;
            case 7:
                dia="Sabado";
                break;
        }
        return dia;
    }

    public void checkIn(){

    }

    /*public List<List> obtenerActividadesPorNombre(String  nombre) {
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

*/

}
