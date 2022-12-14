package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloCentroDeportivo;
import com.Olimpia.demo.modelo.ModeloEmpresa;
import com.Olimpia.demo.modelo.ModeloFile;
import com.Olimpia.demo.modelo.ModeloUsuario;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsuarioAdminController implements Initializable {


    // Variables Empresas

    @FXML
    private TextField nombreEmpresa;

    @FXML
    private TextField direccionEmpresa;

    @FXML
    private TextField emailEmpresa;

    @FXML
    private TextField contraseniaEmpresa;

    @FXML
    private TableView<ModeloEmpresa> tablaEmpresa;

    @FXML
    private TableColumn<ModeloEmpresa, String> colnombreEmpresa;

    @FXML
    private TableColumn<ModeloEmpresa, String> colEmailEmpresa;

    @FXML
    private TableColumn<ModeloEmpresa, String> colPSWEmpresa;

    private ObservableList<ModeloEmpresa> empresasLista;
    //------------------------------------------------------------------------------------------------------------------

    //Variables Centros Deportivos
    @FXML
    private TextField camponombreCentro;

    @FXML
    private TextField campoCorreoCentro;

    @FXML
    private TextField campoContrasena;

    @FXML
    private TextField campoDireccionCentro;

    @FXML
    private Button btnAgregarCentro;

    @FXML
    private Button btnCancelarCentro;

    //------------------------------------------------------------------------------------------------------------------

    //Variables Empleados o Usuarios Finales

    @FXML
    private TextField campoNombreEmpleado;

    @FXML
    private TextField campoCorreoUsuario;

    @FXML
    private TextField campoContrasenaEmpleado;

    @FXML
    private ComboBox comboboxEmpresa;

    @FXML
    private Button btnanadirEmpleado;

    @FXML
    private Button btncancelarEmpleado;
    private Stage estage;

    //------------------------------------------------------------------------------------------------------------------

    //Metodo Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colnombreEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("nombre"));
        this.colEmailEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("email"));
        this.colPSWEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa, String>("psw"));
        actualizarTblEmpresas();
    }
    //------------------------------------------------------------------------------------------------------------------

    //Metodos para Empresas

    @FXML
    protected void a??adirEmpresa() {

        if (nombreEmpresa.getText().equals(null) || contraseniaEmpresa.getText().equals(null) || emailEmpresa.getText().equals(null)) {
            //Hay un error , no se agrega
        } else {
            try {
                String nombreClient = nombreEmpresa.getText();
                String email = emailEmpresa.getText();
                String password = contraseniaEmpresa.getText();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ModeloUsuario usuario = new ModeloUsuario(email,password,1);
                    String strUsuario = mapper.writeValueAsString(usuario);
                    HttpResponse<JsonNode> responseUsr = Unirest.post("http://localhost:8080/vagouy/usuario/registrarUsuario")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(strUsuario)
                            .asJson();
                    System.out.println(responseUsr.getStatus());
                    ModeloEmpresa empresa = new ModeloEmpresa(email, nombreClient, password);
                    String jsonString = mapper.writeValueAsString(empresa);
                    HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/empresa")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(jsonString)
                            .asJson();
                    System.out.println(response.getBody());
                    System.out.println(response.getStatus());
                    System.out.println(response.getStatusText());
                    actualizarTblEmpresas();
                } catch (Exception e) {


                }

            } catch (Exception e) {
            }
        }
    }

    @FXML
    protected ObservableList<ModeloEmpresa> obtenerEmpresas() {
        ObjectMapper mapper = new ObjectMapper();
        String empresas = Unirest.get("http://localhost:8080/vagouy/empresa").asString().getBody();
        List<ModeloEmpresa> empresa = null;
        try {
            empresa = mapper.readValue(empresas, new TypeReference<List<ModeloEmpresa>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<ModeloEmpresa> retorno = FXCollections.observableList(empresa);
        System.out.println(retorno);
        return retorno;
    }


    @FXML
    public void actualizarTblEmpresas() {
        this.tablaEmpresa.setItems(obtenerEmpresas());
    }


    @FXML
    protected void limpiartxtEmpresa() {
        nombreEmpresa.setText(null);
        emailEmpresa.setText(null);
        contraseniaEmpresa.setText(null);
    }

    @FXML
    private void seleccionarEmpresas(MouseEvent event) {

        ModeloEmpresa empresa = (ModeloEmpresa) this.tablaEmpresa.getSelectionModel().getSelectedItem();

        if (empresa.equals(null)) {

        } else {
            this.nombreEmpresa.setText(empresa.getNombre());
            this.emailEmpresa.setText(empresa.getEmail());
            this.contraseniaEmpresa.setText(empresa.getPsw());
        }
    }


    @FXML
    private void modificarEmpresa() {
        ModeloEmpresa empresa = (ModeloEmpresa) this.tablaEmpresa.getSelectionModel().getSelectedItem();

        if (empresa.equals(null)) {

        } else {

            try {
                String nombreCliente = nombreEmpresa.getText();
                String email = emailEmpresa.getText();
                String password = contraseniaEmpresa.getText();


                ModeloEmpresa empresaAux = new ModeloEmpresa(email, nombreCliente, password);

                if (!this.empresasLista.contains(empresaAux)) {
                    empresa.setNombre(empresaAux.getNombre());
                    empresa.setEmail(empresaAux.getEmail());
                    empresa.setPsw(empresaAux.getPsw());


                    actualizarTblEmpresas();


                }


            } catch (Exception e) {

            }
        }


    }


    public void seleccionarEmpresa(javafx.scene.input.MouseEvent mouseEvent) {
        ModeloEmpresa empresa = (ModeloEmpresa) this.tablaEmpresa.getSelectionModel().getSelectedItem();

        if (empresa.equals(null)) {

            System.out.println("ERROR");

        } else {
            this.nombreEmpresa.setText(empresa.getNombre());
            this.emailEmpresa.setText(empresa.getEmail());
            this.contraseniaEmpresa.setText(empresa.getPsw());
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

    public void a??adirCentro(){
        if (camponombreCentro.getText().equals(null) || campoCorreoCentro.getText().equals(null) || campoContrasena.getText().equals(null) || campoDireccionCentro.getText().equals(null)) {
            //Hay un error , no se agrega
        } else {
            try {
                String nombreCD = camponombreCentro.getText();
                String emailCD = campoCorreoCentro.getText();
                String passwordCD = campoContrasena.getText();
                String direccionCD = campoDireccionCentro.getText();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ModeloUsuario usuario = new ModeloUsuario(emailCD,passwordCD,2);
                    String strUsuario = mapper.writeValueAsString(usuario);
                    HttpResponse<JsonNode> responseUsr = Unirest.post("http://localhost:8080/vagouy/usuario/registrarUsuario")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(strUsuario)
                            .asJson();
                    System.out.println(responseUsr.getStatus());
                    ModeloCentroDeportivo empresa = new ModeloCentroDeportivo(emailCD,passwordCD,nombreCD,0L,direccionCD);
                    String jsonString = mapper.writeValueAsString(empresa);
                    HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/CentroDeportivos")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(jsonString)
                            .asJson();
                    System.out.println(response.getBody());
                    System.out.println(response.getStatus());
                    System.out.println(response.getStatusText());
                } catch (Exception e) {


                }

            } catch (Exception e) {
            }
        }
    }

    /*public void subirImagen() {
        File file = new File("src/main/resources/furbo.jpg");
        FileInputStream input = null;
        MultipartFile multipartFile = null;

        ObjectMapper mapper = new ObjectMapper();
        ModeloFile modeloFile = null;
        String json = "";
        try {
            input = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
            modeloFile = new ModeloFile(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
            json = mapper.writeValueAsString(modeloFile);
            System.out.println(json);
            HttpResponse<JsonNode> response = Unirest.post("http://10.252.60.160:8080/Imagen")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(json)
                    .asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //------------------------------------------------------------------------------------------------------------------

    public void setStage(Stage stage) {
        this.estage = stage;
    }

    //Metodos para Empleados o Usuarios Finales
}
