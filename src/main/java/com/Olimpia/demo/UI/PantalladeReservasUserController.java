package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividad;
import com.Olimpia.demo.modelo.ModeloActividadRealizada;
import com.Olimpia.demo.modelo.ModeloEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class PantalladeReservasUserController {

    @FXML
    Stage estage;

    private String emailUser;


    @FXML
    private Button btnVolver;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private GridPane gridpane;

    @FXML
    private ScrollPane scrollpane;

    UsuariosFinalesController controlador;

    private List<Long> idReservas = new ArrayList<>();


    public void setinit(String email, Stage estage, UsuariosFinalesController usuariosFinalesController) {
        this.emailUser = email;
        this.idReservas = obtenerIDReservasdeUsuario(email);
        this.controlador = usuariosFinalesController;
        try {
            int filas = 0;
            for (int i = 0; i < idReservas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ElementoReservasUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);*/
                //anchorpane.setId("pane");
                ElementoReservasUserController controlador = fxmlLoader.getController();
                controlador.setData(obtenerReserva(idReservas.get(i)),email,this);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Long> obtenerIDReservasdeUsuario(String email) {
        ObjectMapper mapper = new ObjectMapper();
        String empresas = Unirest.get("http://localhost:8080/vagouy/actividadRealizada/reserva/cliente/"+email).asString().getBody();
        List<Long> reservas = null;
        try {
            reservas = mapper.readValue(empresas, new TypeReference<List<Long>>() {});
        } catch (Exception e) {
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

    public void setStage(Stage stage) {
        this.estage = stage;
    }

    public void Back(){
        estage.close();
        this.controlador.showWindow();
    }

    public void actualizar() {
        this.idReservas.clear();
        this.gridpane.getChildren().clear();
        this.idReservas = obtenerIDReservasdeUsuario(emailUser);

        try {
            int filas = 0;
            for (int i = 0; i < idReservas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ElementoReservasUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);*/
                //anchorpane.setId("pane");
                ElementoReservasUserController controlador = fxmlLoader.getController();
                controlador.setData(obtenerReserva(idReservas.get(i)),emailUser,this);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
