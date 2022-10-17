package com.Olimpia.demo.UI;


import com.Olimpia.demo.modelo.ModeloActividad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsuariosFinalesController implements Initializable {

    private InicioController controllerInicio;
    private Stage estage;

    @FXML
    private Text textoUsuario;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField buscartxtfield;
    @FXML
    private GridPane gridpane;
    @FXML
    private ScrollPane scrollpane;

    private String texto;

    private List<ModeloActividad> itemAct = new ArrayList<>();

    private List<ModeloActividad> getItemAct(){
        List<ModeloActividad> itemAct = new ArrayList<>();
        ModeloActividad actividad = null;

        return itemAct;
    }



    public void init(String text, Stage stage, InicioController inicioController) {
        this.textoUsuario.setText(text);
        this.controllerInicio = inicioController;
        this.estage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemAct.addAll(getItemAct());
        int filas = 0;

        try {

            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();

                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i));

                gridpane.add(anchorpane,0,filas++);
                GridPane.setMargin(anchorpane,new Insets(10));

            }
        } catch (Exception e){

        }
    }
}
