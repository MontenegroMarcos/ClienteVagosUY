package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;
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
    private Stage estage;
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
    private ComboBox<String> horarioFin;

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

    @FXML
    public void cerrarsesion() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        InicioController controlador = fxmlLoader.getController();
        String css = this.getClass().getResource("inicioestilo.css").toExternalForm();
        scene.getStylesheets().add(css);
        controlador.setStage(stage);
        stage.setTitle("GymAPP");
        stage.show();
        this.estage.close();

    }

    private ObservableList<String> obtenerActividadesdeCD() {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://10.252.60.114:8080/vagouy/Actividades/centro/email/"+this.textoUsuario.getText()).asString().getBody();
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
            this.horarioFin.getItems().clear();
            this.horarioInicio.setItems(obtenerHorasInicio(this.checkinActividades.getValue(),diaSemanaDate()));
        }
    }

    private ObservableList<String> obtenerHorasInicio(String actividad,String dia) {
        ObjectMapper mapper = new ObjectMapper();
        String strHoras = Unirest.get("http://10.252.60.114:8080/vagouy/Actividades/horaInicio/"+actividad+"/"+this.textoUsuario.getText()+"/"+dia).asString().getBody();
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
            this.horarioFin.getItems().clear();
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

    public void seleccionarHoraInicio(){
        this.horarioFin.setItems(obtenerHorasFin(this.checkinActividades.getValue(),diaSemanaDate(),this.horarioInicio.getValue()));
    }

    private ObservableList<String> obtenerHorasFin(String actividad,String dia,String horaInicio) {
        ObjectMapper mapper = new ObjectMapper();
        String strHoras = Unirest.get("http://10.252.60.114:8080/vagouy/Actividades/horaFin/"+actividad+"/"+this.textoUsuario.getText()+"/"+dia+"/"+horaInicio).asString().getBody();
        List<String> horas = null;
        try {
            horas = mapper.readValue(strHoras, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(horas);
        return retorno;
    }

    public void checkIn(){
        if(!this.nombreCheckin.getText().isEmpty() && !this.checkinActividades.getValue().isEmpty() &&
            this.selectorFecha.getValue()!=null && !this.horarioInicio.getValue().isEmpty() &&
            !this.horarioFin.getValue().isEmpty()){
            LocalDate localDate = this.selectorFecha.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            ModeloActividadRealizada actRealizada=new ModeloActividadRealizada(date,obtenerActividad(this.textoUsuario.getText(),this.checkinActividades.getValue()),obtenerHorario(this.checkinActividades.getValue(),this.textoUsuario.getText(),diaSemanaDate(),this.horarioInicio.getValue(),this.horarioFin.getValue()),obtenerEmpleado(this.nombreCheckin.getText()),23);
            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(actRealizada);
                HttpResponse<JsonNode> response = Unirest.post("http://10.252.60.114:8080/vagouy/actividadRealizada/checkIn")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .body(jsonString)
                        .asJson();
                System.out.println(response.getBody());
                System.out.println(response.getStatus());
                System.out.println(response.getStatusText());
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            //Levantar cuadro de texto
        }
    }

    private ModeloActividad obtenerActividad(String email_centro,String nombre){
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://10.252.60.114:8080/vagouy/Actividades/actividad/"+email_centro+"/"+nombre).asString().getBody();
        ModeloActividad actividad = null;
        try {
            actividad = mapper.readValue(strActividad, ModeloActividad.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    private ModeloHorario obtenerHorario(String nombre,String email,String dia,String horaInicio,String horaFin){
        ObjectMapper mapper = new ObjectMapper();
        String strHorario = Unirest.get("http://10.252.60.114:8080/vagouy/Actividades/obtenerHorario/"+nombre+"/"+email+"/"+dia+"/"+horaInicio+"/"+horaFin).asString().getBody();
        ModeloHorario horario = null;
        try {
            horario = mapper.readValue(strHorario, ModeloHorario.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    private ModeloEmpleado obtenerEmpleado(String email){
        ObjectMapper mapper = new ObjectMapper();
        String strEmpleado = Unirest.get("http://10.252.60.114:8080/vagouy/Empleado/"+email).asString().getBody();
        ModeloEmpleado empleado = null;
        try {
            empleado = mapper.readValue(strEmpleado, ModeloEmpleado.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public void setStage(Stage stage) {
        this.estage = stage;
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
