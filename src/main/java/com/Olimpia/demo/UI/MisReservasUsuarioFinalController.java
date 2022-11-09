package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MisReservasUsuarioFinalController implements Initializable {

    @FXML
    private GridPane gridpane;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Text txtUser;

    @FXML
    private Stage estage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setinit(String s, Stage stage) {
        this.txtUser.setText(s);
        estage = stage;
    }

    public void setStage(Stage stage) {
        estage = stage;
    }
}
