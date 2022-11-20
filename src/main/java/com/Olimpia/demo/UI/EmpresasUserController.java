package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloEmpleado;
import com.Olimpia.demo.modelo.ModeloEmpresa;
import com.Olimpia.demo.modelo.ModeloUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpresasUserController implements Initializable {

    public Label labelEmpresa;
    private String correoEmpresa;

    //Tab Nombre
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCrear;


    @FXML
    private TextField buscarNombre;

    @FXML
    private TextField campoCorreo;

    @FXML
    private TextField campoPSW;

    @FXML
    private TextField campoSaldo;

    @FXML
    private TextField camponombre;

    @FXML
    private TableColumn<ModeloEmpleado, String> columnaCorreo;

    @FXML
    private TableColumn<ModeloEmpleado, String> columnaNombre;

    @FXML
    private TableColumn<ModeloEmpleado, String> columnaSaldo;

    @FXML
    private Stage estage;

    @FXML
    private InicioController controlador;
    @FXML
    private Label gastoMensualLabel;

    @FXML
    private LineChart<?, ?> graficalineal;

    @FXML
    private TableView<ModeloEmpleado> tablaEmpleados;

    @FXML
    private Button btnModificar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.labelEmpresa.setText("");

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado,String>("nombre"));
        this.columnaCorreo.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado,String>("email"));
        this.columnaSaldo.setCellValueFactory(new PropertyValueFactory<ModeloEmpleado,String>("saldoMensual"));
        actualizarTblEmpleados();
    }

    public void init(String email_empresa, Stage stage, InicioController inicioController) {
        this.labelEmpresa.setText(email_empresa);
        this.estage = stage;
        this.controlador = inicioController;

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



    public ObservableList<ModeloEmpleado> obtenerEmpleados(){
        return null;
    }

    @FXML
    public void actualizarTblEmpleados() {
        this.tablaEmpleados.setItems(obtenerEmpleados());
    }


    public void AddEmpleados() {
        if (this.camponombre.getText().equals(null) || this.campoPSW.getText().equals(null) || this.campoCorreo.getText().equals(null)
                || (this.campoSaldo.getText().equals(null))) {
            //Hay un error , no se agrega

        } else {
            try {
                String nombreCliente = camponombre.getText();
                String email = campoCorreo.getText();
                String password = campoPSW.getText();
                Long saldoMensual = Long.parseLong(campoSaldo.getText());
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ModeloUsuario usuario = new ModeloUsuario(email,password,3);
                    String jsonString = mapper.writeValueAsString(usuario);
                    HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/usuario/registrarUsuario")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(jsonString)
                            .asJson();
                    System.out.println(response.getStatus());
                    ModeloEmpleado empleado = new ModeloEmpleado(email,nombreCliente,password,saldoMensual,0L,0L,obtenerEmpresa(this.labelEmpresa.getText()));
                    String empleadoJson = mapper.writeValueAsString(empleado);
                    HttpResponse<JsonNode> responseEmpleado = Unirest.post("http://localhost:8080/vagouy/Empleado")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(empleadoJson)
                            .asJson();
                    System.out.println(responseEmpleado.getStatus());
                } catch (Exception e) {

                }
            } catch (Exception e) {
            }

        }

    }

    private ModeloEmpresa obtenerEmpresa(String email){
        ObjectMapper mapper = new ObjectMapper();
        String strEmpresa = Unirest.get("http://localhost:8080/vagouy/empresa/"+email).asString().getBody();
        ModeloEmpresa empresa = null;
        try {
            empresa = mapper.readValue(strEmpresa, ModeloEmpresa.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresa;
    }

    @FXML
    protected void limpiartxtEmpresa() {
        this.camponombre.setText(null);
        this.campoCorreo.setText(null);
        this.campoPSW.setText(null);
        this.campoSaldo.setText(null);
    }

    @FXML
    public void modificarEmpleados() throws IOException {

        ModeloEmpleado empelado = (ModeloEmpleado) this.tablaEmpleados.getSelectionModel().getSelectedItem();

        if(empelado.equals(null)){

        }else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("modificarEmpleadodesdeEmpresa.fxml"));
            Parent root = fxmlLoader.load();
            ModificarEmpleadodesdeEmpresaController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
            scene.getStylesheets().add(css);


            stage.setScene(scene);
            controller.init(empelado.getNombre(),empelado.getEmail(),empelado.getPsw(),
                    String.valueOf(empelado.getSaldoActual()));
            stage.show();
        }

    }



}

