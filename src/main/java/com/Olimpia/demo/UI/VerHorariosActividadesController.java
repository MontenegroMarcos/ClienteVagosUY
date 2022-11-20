package com.Olimpia.demo.UI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kong.unirest.Unirest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VerHorariosActividadesController implements Initializable {
    @FXML
    private GridPane gridpane;

    @FXML
    private Label titulo;

    @FXML
    private Stage estage;

    ActividadesItemUserController controlador;

    private String nombreActividad;

    private String nombreCD;

    private String emailCD;

    private List<List> listaHorarios = new ArrayList<>();

    public void init(String nombreActividad, String nombreCD, String emailCd, String emailEmpleado) {
        this.titulo.setText("Entrena " + nombreActividad + " en " + nombreCD);
        this.nombreActividad = nombreActividad;
        this.nombreCD = nombreCD;
        this.emailCD = emailCd;
        this.listaHorarios = obtenerHorarios(this.nombreActividad, this.emailCD);
        int filas = 0;

        try {

            for (int i = 0; i < listaHorarios.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Horarios.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();

                HorariosController controlador = fxmlLoader.getController();
                controlador.setData(listaHorarios.get(i), emailCd, nombreActividad, emailEmpleado);

                gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.titulo.setText("");
    }

    private List<List> obtenerHorarios(String nombreActividad, String emailCD) {
        ObjectMapper mapper = new ObjectMapper();
        String strHorarios = Unirest.get("http://localhost:8080/vagouy/Actividades/horarios/" + emailCD + "/" + nombreActividad).asString().getBody();
        List<List> horarios = null;
        try {
            horarios = mapper.readValue(strHorarios, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horarios;
    }

    public void setConfig(Stage stage, ActividadesItemUserController actividadesItemUserController) {
        this.estage = stage;
        this.controlador = actividadesItemUserController;
    }

  /*  @FXML
    void Back(){
        controlador.show();
        estage.close();
    }

   */
}
