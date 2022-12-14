package com.Olimpia.demo.UI;

import ch.qos.logback.core.util.COWArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.Interceptor;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.PrinterGraphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CentroDeportivoController implements Initializable {

    @FXML
    public TableColumn<MostrarActividad,String> tablaDiaGestion;
    @FXML
    public TableColumn<MostrarActividad,String> columnaHoraInicio;

    @FXML
    public TableColumn<MostrarActividad,String> colHoraFinal;

    @FXML
    public TableColumn<MostrarActividad,String> colCupos;
    private String emailCentroDeportivo;

    public CheckBox checkbox;

    public ImageView imageview;

    public Button btnSubirImagen;
    @FXML
    public GridPane gridpane;
    @FXML
    public ComboBox<String> comboBoxHorarioInicial;

    @FXML
    public ComboBox<String> comboBoxhorarioFinal;
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
    private ComboBox<String> comboboxCategoriasAgregar;

    @FXML
    private ComboBox<String> comboxDiasAgregar;

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
    private TableColumn<MostrarActividad, String> preciotablaGestion;

    @FXML
    private TableView<MostrarActividad> tablaGestionActividades;

    @FXML
    private TableColumn<?, ?> tablaHorariosGestionar;

    @FXML
    private TableColumn<MostrarActividad, String> tablanombreGestion;

    @FXML
    private Text textoUsuario;

    @FXML
    private ComboBox<String> horarioInicio;

    @FXML
    private DatePicker selectorFecha;
    private List<idActEmpl> itemIDReservas;

    private File imagenFile;
    private ModeloCentroDeportivo centroDeportivo;
    private List<ModeloHorario> horariosAgregarRegistro;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
        this.colnombreEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("nombre"));
        this.colEmailEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("email"));
        this.colPSWEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("psw"));
         */

        //this.tablanombreGestion.setCellValueFactory(new PropertyPermission());





        this.cuposAgregar.setManaged(false);
        this.cuposAgregar.setVisible(false);

        this.cuposAgregar.managedProperty().bind(this.checkbox.selectedProperty());
        this.cuposAgregar.visibleProperty().bind(this.checkbox.selectedProperty());
        if(cuposAgregar.managedProperty().getValue()){
            this.cuposAgregar.setText(null);
        }

        this.itemIDReservas = new ArrayList<>();
        this.horariosAgregarRegistro = new ArrayList<>();
        ObservableList<String> lista = FXCollections.observableArrayList("Todas", "Futbol", "Basketball", "Tenis", "Otros");
        this.comboboxCategoriasAgregar.setItems(lista);
        ObservableList<String> lista1 = FXCollections.observableArrayList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes","Sabado","Domingo");
        this.comboxDiasAgregar.setItems(lista1);

        this.tablanombreGestion.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("nombre"));
        this.preciotablaGestion.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("precio"));
        this.tablaDiaGestion.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("dia"));
        this.columnaHoraInicio.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("horaInicio"));
        this.colHoraFinal.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("horaFin"));
        this.colCupos.setCellValueFactory(new PropertyValueFactory<MostrarActividad, String>("cupos"));

        this.imagenFile = new File("src/main/resources/defaultImage.jpg");
    }

    public void controlHorafinal(){
        this.comboBoxhorarioFinal.getItems().clear();
        List<String> horafin = new ArrayList<>();
        for (int i = Integer.parseInt(this.comboBoxHorarioInicial.getValue())+1; i <= 23; i++) {
            horafin.add(String.valueOf(i));
        }
        this.comboBoxhorarioFinal.setItems(FXCollections.observableList(horafin));
    }

    public void init(String emailCD){
        this.emailCentroDeportivo = emailCD;
        this.textoUsuario.setText(emailCD);
        this.checkinActividades.setItems(obtenerActividadesdeCD());
        this.balancefinal.setText(obtenerBalanace(emailCD).toString());
        this.centroDeportivo = obtenerCentro(emailCD);
        this.itemIDReservas.addAll(obtenerIDReservas(emailCD));

        List<String> horaInicio = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            horaInicio.add(String.valueOf(i));
        }
        this.comboBoxHorarioInicial.setItems(FXCollections.observableList(horaInicio));
        this.actualizarTblEmpresas();

        /*ObservableList<String> horafin = null;
        for (int i = (int) this.comboBoxHorarioInicial.getValue(); i < 23; i++) {
            horafin.add(String.valueOf(i));
        }
        this.comboBoxhorarioFinal.setItems(horafin);*/



        try {
            int filas = 0;
            for (int i = 0; i < itemIDReservas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("elementoReservaCD.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                //anchorpane.setId("pane");*/
                ElementoReservaCDcontroller controlador = fxmlLoader.getController();
                controlador.setData(obtenerReserva(itemIDReservas.get(i).getIdActividad()),obtenerEmpleado(itemIDReservas.get(i).getEmailEmpleado()));
                controlador.setData(obtenerReserva(itemIDReservas.get(i).getIdActividad()),obtenerEmpleado(itemIDReservas.get(i).getEmailEmpleado()));
                controlador.setcontrol(this);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ModeloCentroDeportivo obtenerCentro(String email){
        ObjectMapper mapper = new ObjectMapper();
        String strCentro = Unirest.get("http://localhost:8080/vagouy/CentroDeportivos/getCentro/"+email).asString().getBody();
        ModeloCentroDeportivo centro = null;
        try {
            centro = mapper.readValue(strCentro, ModeloCentroDeportivo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centro;
    }

    private Long obtenerBalanace(String emailCD){
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://localhost:8080/vagouy/CentroDeportivos/balance/"+emailCD).asString().getBody();
        Long balance = null;
        try {
            balance = mapper.readValue(strActividad, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void actualizarBalance(){
        this.balancefinal.setText(obtenerBalanace(this.textoUsuario.getText()).toString());
    }

    private List<ModeloActividad> obtenerActividades(String emailCD) {
        ObjectMapper mapper = new ObjectMapper();
        String strActividades = Unirest.get("http://localhost:8080/vagouy/Actividades/centro/getActividad/"+emailCD).asString().getBody();
        List<ModeloActividad> actividades = null;
        try {
            actividades = mapper.readValue(strActividades, new TypeReference<List<ModeloActividad>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividades;
    }

    private List<idActEmpl> obtenerIDReservas(String emailCD) {
        Date fechaInicio = new Date(System.currentTimeMillis());
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        Date fechaFin = null;
        try{
            fechaFin = format1.parse(format1.format(fechaInicio));
        }catch (Exception e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFin);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR, 23);
        fechaFin = calendar.getTime();
        CentroFechas centroFechas = new CentroFechas(emailCD,fechaInicio,fechaFin);
        List<idActEmpl> reservas = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(centroFechas);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/reserva/centro")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(jsonString)
                    .asJson();
            String hola = response.getBody().toString();
            reservas = mapper.readValue(response.getBody().toString(), new TypeReference<List<idActEmpl>>() {});
        }catch(Exception e){
            e.printStackTrace();
        }
        return reservas;
    }

    private ModeloActividadRealizada obtenerReserva(Long id){
        ObjectMapper mapper = new ObjectMapper();
        String strReserva = Unirest.get("http://localhost:8080/vagouy/actividadRealizada/getById/"+id.toString()).asString().getBody();
        ModeloActividadRealizada reserva = null;
        try {
            reserva = mapper.readValue(strReserva, ModeloActividadRealizada.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reserva;
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
            this.horarioFin.getItems().clear();
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
        String strHoras = Unirest.get("http://localhost:8080/vagouy/Actividades/horaFin/"+actividad+"/"+this.textoUsuario.getText()+"/"+dia+"/"+horaInicio).asString().getBody();
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
            ModeloHorario horario = obtenerHorario(this.checkinActividades.getValue(),this.textoUsuario.getText(),diaSemanaDate(),this.horarioInicio.getValue(),this.horarioFin.getValue());
            ModeloActividadRealizada actRealizada=new ModeloActividadRealizada(date,obtenerActividad(this.textoUsuario.getText(),this.checkinActividades.getValue()),horario,obtenerEmpleado(this.nombreCheckin.getText()),horario.getKey().getCupos(),null);
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
                if(response.getStatus()==200){
                    this.actualizarBalance();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            //FIXME Levantar cuadro de texto
        }
    }

    private ModeloActividad obtenerActividad(String email_centro,String nombre){
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://localhost:8080/vagouy/Actividades/actividad/"+email_centro+"/"+nombre).asString().getBody();
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
        String strHorario = Unirest.get("http://localhost:8080/vagouy/Actividades/obtenerHorario/"+nombre+"/"+email+"/"+dia+"/"+horaInicio+"/"+horaFin).asString().getBody();
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
        String strEmpleado = Unirest.get("http://localhost:8080/vagouy/Empleado/"+email).asString().getBody();
        ModeloEmpleado empleado = null;
        try {
            empleado = mapper.readValue(strEmpleado, ModeloEmpleado.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public void agregarHorario(){
        if(this.comboxDiasAgregar.getValue().equals(null)||this.comboBoxHorarioInicial.getValue().equals(null)||this.comboBoxhorarioFinal.getValue().equals(null)){
            //Error agregarHorario
        }else{
            String cupos="";
            if(!cuposAgregar.managedProperty().getValue()){
                cupos="-";
                this.horariosAgregarRegistro.add(new ModeloHorario(new horarioKey(this.comboxDiasAgregar.getValue(),this.comboBoxHorarioInicial.getValue(),this.comboBoxhorarioFinal.getValue(),-1)));
            }else{
                try {
                    cupos = this.cuposAgregar.getText();
                    this.horariosAgregarRegistro.add(new ModeloHorario(new horarioKey(this.comboxDiasAgregar.getValue(), this.comboBoxHorarioInicial.getValue(), this.comboBoxhorarioFinal.getValue(), Integer.valueOf(this.cuposAgregar.getText()))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            String texto = "\n"+this.comboxDiasAgregar.getValue()+","+this.comboBoxHorarioInicial.getValue()+","+this.comboBoxhorarioFinal.getValue()+","+cupos;
            Text nuevoTexto = new Text(texto);
            this.DiasyHorarios.getChildren().add(nuevoTexto);
        }
    }

    public void setStage(Stage stage) {
        this.estage = stage;
    }

    public void a??adirActividad(){
        if(this.nombreActividadAgregar.getText().equals(null)||this.comboboxCategoriasAgregar.getValue().equals(null)||this.horariosAgregarRegistro.size()==0||this.precioAgregar.getText().equals(null)||this.imagenFile.equals(null)){
            //Pantalla error
        }else{
            ObjectMapper mapper = new ObjectMapper();
            ModeloActividad actividad = new ModeloActividad(new ActividadKey(this.centroDeportivo,this.nombreActividadAgregar.getText()),this.comboboxCategoriasAgregar.getValue(),Long.parseLong(this.precioAgregar.getText()),this.horariosAgregarRegistro);
            try {
                String strActividad = mapper.writeValueAsString(actividad);
                HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/Actividades")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .body(strActividad)
                        .asJson();
                if(response.getStatus()==200){
                    a??adirImagenActividad(actividad.getKey().getNombre());
                    this.actualizarTblEmpresas();
                }else{
                    //Error ya existe actividad
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void a??adirImagenActividad(String nombreActividad){
        FileInputStream input = null;
        MultipartFile multipartFile = null;

        ObjectMapper mapper = new ObjectMapper();
        ModeloFile modeloFile = null;
        String json = "";
        try {
            input = new FileInputStream(this.imagenFile);
            multipartFile = new MockMultipartFile("file", this.imagenFile.getName(), Files.probeContentType(this.imagenFile.toPath()), IOUtils.toByteArray(input));
            modeloFile = new ModeloFile(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes(),obtenerActividad(this.textoUsuario.getText(),nombreActividad));
            json = mapper.writeValueAsString(modeloFile);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/Imagen")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(json)
                    .asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void elegirImagen(){
        FileChooser seleccionar = new FileChooser();
        seleccionar.setTitle("elegir imagen");
        seleccionar.setInitialDirectory(new File(System.getProperty("user.home")));

        seleccionar.getExtensionFilters().clear();
        seleccionar.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files","*.png","*.jpg","*.gif"));

        this.imagenFile = seleccionar.showOpenDialog(null);

        if(this.imagenFile != null){
            this.imageview.setImage(new Image(this.imagenFile.toURI().toString()));
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

    public void actualizar() {

        this.gridpane.getChildren().clear();
        this.itemIDReservas.clear();

        this.itemIDReservas.addAll(obtenerIDReservas(this.emailCentroDeportivo));

        try {
            int filas = 0;
            for (int i = 0; i < itemIDReservas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("elementoReservaCD.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                //anchorpane.setId("pane");*/
                ElementoReservaCDcontroller controlador = fxmlLoader.getController();
                controlador.setData(obtenerReserva(itemIDReservas.get(i).getIdActividad()),obtenerEmpleado(itemIDReservas.get(i).getEmailEmpleado()));
                controlador.setData(obtenerReserva(itemIDReservas.get(i).getIdActividad()),obtenerEmpleado(itemIDReservas.get(i).getEmailEmpleado()));
                controlador.setcontrol(this);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarTblEmpresas() {
        List<ModeloActividad> actividades = obtenerActividades(this.emailCentroDeportivo);
        List<MostrarActividad> actividadesMostrar = new ArrayList<>();
        ModeloActividad temp=null;
        ModeloHorario tempH=null;
        for(int i=0;i<actividades.size();i++){
            temp=actividades.get(i);
            for(int j=0;j<temp.getHorarios().size();j++){
                tempH=temp.getHorarios().get(j);
                actividadesMostrar.add(new MostrarActividad(temp.getKey().getNombre(),temp.getPrecio().toString(),tempH.getKey().getDia(),tempH.getKey().getHoraInicio(),tempH.getKey().getHoraFin(), tempH.getKey().getCupos().toString()));
            }
        }
        ObservableList<MostrarActividad> lista = FXCollections.observableList(actividadesMostrar);
        this.tablaGestionActividades.setItems(lista);
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
