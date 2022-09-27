package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class InicioController {


    private Stage estage;

    @FXML
    private Button CentroDeportivobtn;

    @FXML
    private Button UsuarioAdmin;


    @FXML
    void entrarUsuarioCentroDeportivo() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("CentroDeportivoUI.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


        /*FXMLLoader fxmlLoader = new FXMLLoader(InicioController.class.getResource("CentroDeportivoUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        Stage stage = null;
        stage.setTitle("GymAPP");
        stage.setScene(scene);
        stage.show();*/
    }


    @FXML
    void entrarUsuarioAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("UsuarioAdminUI.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        this.estage.close();

    }

    public void setStage(Stage stage) {
        estage = stage;
    }


    /*@FXML
    void entrarUsuarioAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("UsuarioAdminUI.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }*/


}
