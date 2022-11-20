package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PantalladeReservasUserController {

    @FXML
    Stage estage;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private GridPane gridpane;

    @FXML
    private ScrollPane scrollpane;

    UsuariosFinalesController controlador;

    private List<List> itemAct = new ArrayList<>();
    public void setinit(String nombreUser, Stage estage, UsuariosFinalesController usuariosFinalesController) {
        this.itemAct = obtenerReservasdeUsuario(nombreUser);
        this.controlador = usuariosFinalesController;
        try {
            int filas = 0;
            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ElementoReservasUserController.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                /*String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);*/
                //anchorpane.setId("pane");
                ElementoReservasUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i),nombreUser);

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<List> obtenerReservasdeUsuario(String nombreUser) {
        return null;
    }

    public void setStage(Stage stage) {
        this.estage = stage;
    }

    void Back(){
        estage.close();
        this.controlador.showWindow();
    }
}
