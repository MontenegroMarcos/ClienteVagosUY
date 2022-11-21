package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CentroDeportivoController implements Initializable {

    public CheckBox checkbox;

    public ImageView imageview;

    public Button btnSubirImagen;
    @FXML
    public GridPane gridpane;
    @FXML
    public ComboBox comboBoxHorarioInicial;

    @FXML
    public ComboBox comboBoxhorarioFinal;
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
    private List<List> itemActReservadas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cuposAgregar.setManaged(false);
        this.cuposAgregar.setVisible(false);

        this.cuposAgregar.managedProperty().bind(this.checkbox.selectedProperty());
        this.cuposAgregar.visibleProperty().bind(this.checkbox.selectedProperty());
        if (cuposAgregar.managedProperty().getValue()) {
            this.cuposAgregar.setText(null);
        }

        /*
        this.campoPSW.setManaged(false);
        this.campoPSW.setVisible(false);

        this.campoPSW.managedProperty().bind(this.checkbox.selectedProperty());
        this.campoPSW.visibleProperty().bind(this.checkbox.selectedProperty());
         */

    }

    public void init(String emailCD) {
        this.textoUsuario.setText(emailCD);
        this.checkinActividades.setItems(obtenerActividadesdeCD());
        this.balancefinal.setText(obtenerBalanace(emailCD).toString());
        this.itemActReservadas.addAll(obtenerActividades());

        ObservableList<String> horaInicio = null;
        for (int i = 0; i < 23; i++) {
            horaInicio.add(String.valueOf(i));
        }
        this.comboBoxHorarioInicial.setItems(horaInicio);

        ObservableList<String> horafin = null;
        for (int i = (int) this.comboBoxHorarioInicial.getValue(); i < 23; i++) {
            horafin.add(String.valueOf(i));
        }
        this.comboBoxhorarioFinal.setItems(horafin);



        try {
            int filas = 0;
            for (int i = 0; i < itemActReservadas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("elementoReservaCD.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);*/
                //anchorpane.setId("pane");
                ElementoReservaCDcontroller controlador = fxmlLoader.getController();
                controlador.setData(itemActReservadas.get(i), textoUsuario.getText(), this);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long obtenerBalanace(String emailCD) {
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://localhost:8080/vagouy/CentroDeportivos/balance/" + emailCD).asString().getBody();
        Long balance = null;
        try {
            balance = mapper.readValue(strActividad, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    private List<List> obtenerActividades() {
        return null;
    }


    private ObservableList<String> obtenerActividadesdeCD() {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/centro/email/" + this.textoUsuario.getText()).asString().getBody();
        List<String> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(actividad);
        return retorno;
    }

    public void seleccionActividad() {
        if (this.selectorFecha.getValue() != null) {
            this.horarioInicio.getItems().clear();
            this.horarioFin.getItems().clear();
            this.horarioInicio.setItems(obtenerHorasInicio(this.checkinActividades.getValue(), diaSemanaDate()));
        }
    }

    private ObservableList<String> obtenerHorasInicio(String actividad, String dia) {
        ObjectMapper mapper = new ObjectMapper();
        String strHoras = Unirest.get("http://localhost:8080/vagouy/Actividades/horaInicio/" + actividad + "/" + this.textoUsuario.getText() + "/" + dia).asString().getBody();
        List<String> horas = null;
        try {
            horas = mapper.readValue(strHoras, new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(horas);
        return retorno;
    }

    public void seleccionarDia() {
        if (this.checkinActividades != null) {
            this.horarioInicio.getItems().clear();
            this.horarioFin.getItems().clear();
            this.horarioInicio.setItems(obtenerHorasInicio(this.checkinActividades.getValue(), diaSemanaDate()));
        }
    }

    private String diaSemanaDate() {
        LocalDate localDate = this.selectorFecha.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);
        String dia = "";
        switch (diaSemana) {
            case 1:
                dia = "Domingo";
                break;
            case 2:
                dia = "Lunes";
                break;
            case 3:
                dia = "Martes";
                break;
            case 4:
                dia = "Miercoles";
                break;
            case 5:
                dia = "Jueves";
                break;
            case 6:
                dia = "Viernes";
                break;
            case 7:
                dia = "Sabado";
                break;
        }
        return dia;
    }

    public void seleccionarHoraInicio() {
        this.horarioFin.setItems(obtenerHorasFin(this.checkinActividades.getValue(), diaSemanaDate(), this.horarioInicio.getValue()));
    }

    private ObservableList<String> obtenerHorasFin(String actividad, String dia, String horaInicio) {
        ObjectMapper mapper = new ObjectMapper();
        String strHoras = Unirest.get("http://localhost:8080/vagouy/Actividades/horaFin/" + actividad + "/" + this.textoUsuario.getText() + "/" + dia + "/" + horaInicio).asString().getBody();
        List<String> horas = null;
        try {
            horas = mapper.readValue(strHoras, new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> retorno = FXCollections.observableList(horas);
        return retorno;
    }

    public void checkIn() {
        if (!this.nombreCheckin.getText().isEmpty() && !this.checkinActividades.getValue().isEmpty() &&
                this.selectorFecha.getValue() != null && !this.horarioInicio.getValue().isEmpty() &&
                !this.horarioFin.getValue().isEmpty()) {
            LocalDate localDate = this.selectorFecha.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            ModeloHorario horario = obtenerHorario(this.checkinActividades.getValue(), this.textoUsuario.getText(), diaSemanaDate(), this.horarioInicio.getValue(), this.horarioFin.getValue());
            ModeloActividadRealizada actRealizada = new ModeloActividadRealizada(date, obtenerActividad(this.textoUsuario.getText(), this.checkinActividades.getValue()), horario, obtenerEmpleado(this.nombreCheckin.getText()), horario.getKey().getCupos(), obtenerEmpleado(this.nombreCheckin.getText()));
            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(actRealizada);
                HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/checkIn")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .body(jsonString)
                        .asJson();
                System.out.println(response.getBody());
                System.out.println(response.getStatus());
                System.out.println(response.getStatusText());
                if (response.getStatus() == 200) {
                    this.balancefinal.setText(obtenerBalanace(this.textoUsuario.getText()).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //Levantar cuadro de texto
        }
    }

    private ModeloActividad obtenerActividad(String email_centro, String nombre) {
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://localhost:8080/vagouy/Actividades/actividad/" + email_centro + "/" + nombre).asString().getBody();
        ModeloActividad actividad = null;
        try {
            actividad = mapper.readValue(strActividad, ModeloActividad.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    private ModeloHorario obtenerHorario(String nombre, String email, String dia, String horaInicio, String horaFin) {
        ObjectMapper mapper = new ObjectMapper();
        String strHorario = Unirest.get("http://localhost:8080/vagouy/Actividades/obtenerHorario/" + nombre + "/" + email + "/" + dia + "/" + horaInicio + "/" + horaFin).asString().getBody();
        ModeloHorario horario = null;
        try {
            horario = mapper.readValue(strHorario, ModeloHorario.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    private ModeloEmpleado obtenerEmpleado(String email) {
        ObjectMapper mapper = new ObjectMapper();
        String strEmpleado = Unirest.get("http://localhost:8080/vagouy/Empleado/" + email).asString().getBody();
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


    public void elegirImagen() {
        FileChooser seleccionar = new FileChooser();
        seleccionar.setTitle("elegir imagen");
        seleccionar.setInitialDirectory(new File(System.getProperty("user.home")));

        seleccionar.getExtensionFilters().clear();
        seleccionar.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files", "*.png", "*.jpg", "*.gif"));

        File file = seleccionar.showOpenDialog(null);

        if (file != null) {
            //this.imageview.setImage(new Image(file.toURI().toString()));
            FileInputStream input = null;
            MultipartFile multipartFile = null;

            ObjectMapper mapper = new ObjectMapper();
            ModeloFile modeloFile = null;
            String json = "";
            try {
                input = new FileInputStream(file);
                multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
                modeloFile = new ModeloFile(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes(), obtenerActividad(this.textoUsuario.getText(), this.nombreActividadAgregar.getText()));
                json = mapper.writeValueAsString(modeloFile);
                HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/Imagen")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .body(json)
                        .asJson();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void cerrarSesion() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        InicioController controlador = fxmlLoader.getController();

        String css = this.getClass().getResource("usuariosfinalesEstilo.css").toExternalForm();
        scene.getStylesheets().add(css);
        controlador.setStage(stage);

        stage.setTitle("GymAPP");

        stage.show();
        this.estage.close();
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
