package com.Olimpia.demo.UI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UsuarioAdminController {

    @FXML
    private TextField nombreEmpresa;

    @FXML
    private TextField direccionEmpresa;

    @FXML
    private TextField emailEmpresa;

    @FXML
    private TextField contraseniaEmpresa;

    @FXML
    private TableView tablaEmpresa;

    @FXML
    private TableColumn colnombreEmpresa;

    @FXML
    private TableColumn colEmailEmpresa;

    @FXML
    private TableColumn colPSWEmpresa;


    @FXML
    protected void aniadirEmpresa() {

        if (nombreEmpresa.getText().equals(null) || contraseniaEmpresa.getText().equals(null) || emailEmpresa.getText().equals(null)) {
            //Hay un error , no se agrega
        } else {
            String json = "";
            try {
                String nombreClient = nombreEmpresa.getText();
                String email = emailEmpresa.getText();
                String password = contraseniaEmpresa.getText();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode empresa = mapper.createObjectNode();
                    empresa.put("nombre", nombreClient);
                    empresa.put("email",email);
                    empresa.put("psw",password);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(empresa);
                    HttpResponse<JsonNode> response = Unirest.post("http://10.252.60.160:8080/vagouy/empresa")
                            .header("Content-Type", "application/json;charset=utf-8")
                            .body(json)
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



    @FXML
    protected void limpiartxtEmpresa(){
        nombreEmpresa.setText(null);
        emailEmpresa.setText(null);
        contraseniaEmpresa.setText(null);


    }


}
