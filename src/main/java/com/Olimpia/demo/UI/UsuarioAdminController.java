package com.Olimpia.demo.UI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;

import java.net.http.HttpResponse;

public class UsuarioAdminController {

    @FXML
    private TextField nombreEmpresa;

    @FXML
    private TextField direccionEmpresa;

    @FXML
    private TextField emailEmpresa;

    @FXML
    private TextField telefonoEmpresa;

    @FXML
    private TableView tablaEmpresa;


    @FXML
    protected void aniadirPersona() {

        if (nombreEmpresa.getText().equals(null)) {
            //Hay un error , no se agrega
        } else {
            String json = "";
            try {
                String nombreClient = nombreEmpresa.getText();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectNode resto = mapper.createObjectNode();
                    resto.put("nombre", nombreClient);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resto);
                        /*HttpResponse<JsonNode> response = Unirest.post("")
                                .header("Content-Type", "application/json;charset=utf-8")
                                .body(json)
                                .asJson();
                        System.out.println(response.getBody());
                        System.out.println(response.getStatus());
                        System.out.println(response.getStatusText());*/
                } catch (Exception e) {

                }

            } catch (Exception e) {
            }
        }
    }



    @FXML
    protected void limpiartxtEmpresa(){
        nombreEmpresa.setText(null);

    }


}
