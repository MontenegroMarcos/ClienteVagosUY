package com.Olimpia.demo.UI;

import com.Olimpia.demo.ModeloEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsuarioAdminController implements Initializable {

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
    private TableColumn<ModeloEmpresa,String> colnombreEmpresa;

    @FXML
    private TableColumn<ModeloEmpresa,String> colEmailEmpresa;

    @FXML
    private TableColumn<ModeloEmpresa,String> colPSWEmpresa;

    private ObservableList<ModeloEmpresa> empresasLista;

    @FXML
    protected void añadirEmpresa() {

        if (nombreEmpresa.getText().equals(null) || contraseniaEmpresa.getText().equals(null) || emailEmpresa.getText().equals(null)) {
            //Hay un error , no se agrega
        } else {
            try {
                String nombreClient = nombreEmpresa.getText();
                String email = emailEmpresa.getText();
                String password = contraseniaEmpresa.getText();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ModeloEmpresa empresa = new ModeloEmpresa(email,nombreClient,password);
                    String jsonString = mapper.writeValueAsString(empresa);
                    HttpResponse<JsonNode> response = Unirest.post("http://10.252.60.160:8080/vagouy/empresa")
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
    protected ObservableList<ModeloEmpresa> obtenerEmpresas(){
        ObjectMapper mapper = new ObjectMapper();
        String empresas = Unirest.get("http://10.252.60.160:8080/vagouy/empresa").asString().getBody();
        List<ModeloEmpresa> empresa = null;
        try {
            empresa = mapper.readValue(empresas, new TypeReference<List<ModeloEmpresa>>() {});
        }catch(Exception e){
            e.printStackTrace();
        }
        ObservableList<ModeloEmpresa> retorno = FXCollections.observableList(empresa);
        System.out.println(retorno);
        return retorno;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colnombreEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa,String>("nombre"));
        this.colEmailEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa,String>("email"));
        this.colPSWEmpresa.setCellValueFactory(new PropertyValueFactory<ModeloEmpresa,String>("psw"));
    }

    @FXML
    public void actualizarTblEmpresas(){
        this.tablaEmpresa.setItems(obtenerEmpresas());
    }



    @FXML
    protected void limpiartxtEmpresa(){
        nombreEmpresa.setText(null);
        emailEmpresa.setText(null);
        contraseniaEmpresa.setText(null);
    }

    @FXML
    private void seleccionarEmpresas(MouseEvent event){

        ModeloEmpresa empresa = (ModeloEmpresa) this.tablaEmpresa.getSelectionModel().getSelectedItem();

        if(empresa.equals(null)){

        } else {
            this.nombreEmpresa.setText(empresa.getNombre());
            this.emailEmpresa.setText(empresa.getEmail());
            this.contraseniaEmpresa.setText(empresa.getPsw());
        }
    }


    @FXML
    private void modificarEmpresa(){
        ModeloEmpresa empresa = (ModeloEmpresa) this.tablaEmpresa.getSelectionModel().getSelectedItem();

        if(empresa.equals(null)){

        } else {

            try {
                String nombreCliente = nombreEmpresa.getText();
                String email = emailEmpresa.getText();
                String password = contraseniaEmpresa.getText();


                ModeloEmpresa empresaAux = new ModeloEmpresa(email,nombreCliente,password);

                if(!this.empresasLista.contains(empresaAux)){
                    empresa.setNombre(empresaAux.getNombre());
                    empresa.setEmail(empresaAux.getEmail());
                    empresa.setPsw(empresaAux.getPsw());


                    this.tablaEmpresa.refresh();


                }


            }catch (Exception e){

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
}
