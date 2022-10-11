package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
 import javafx.scene.control.Label;

import java.awt.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {
    private Stage estage;

    @FXML
    private Label alertLabel;

    @FXML
    private Button CentroDeportivobtn;

    @FXML
    private Button UsuarioAdmin;

    @FXML
    private ToggleButton mostrarOcultar;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoPassword;

    @FXML
    private Button login;

    @FXML
    private Label verdaderapassword;
    @FXML
    void entrarUsuarioCentroDeportivo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("CentroDeportivoUI.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void entrarUsuarioAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(UsuarioAdminController.class.getResource("UsuarioAdminUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        this.estage.close();
    }

    @FXML
    void entrarUsuarioFinal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(UsuarioAdminController.class.getResource("usuariosFinales.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        this.estage.close();
    }


    public void setStage(Stage stage) {
        estage = stage;
        //guarda info pantalla
    }
    @FXML
   public void mostrarContaseña(ActionEvent event){
        if(mostrarOcultar.isSelected()){
            verdaderapassword.setVisible(true);
            verdaderapassword.setText(campoPassword.getText());
            mostrarOcultar.setText("Ocultar");
        } else {
            verdaderapassword.setVisible(false);
            mostrarOcultar.setText("Mostrar");
        }
   }
   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void mostrarContasenia(javafx.event.ActionEvent actionEvent) {
    }
    @FXML
    public void entrarUsuariofinalLogin(){

        String correo = campoEmail.getText();
        String psw = campoPassword.getText();

        if(validarlogin(correo,psw)){
            //Desarollo
        } else {
            alertLabel.setText("Error, correo y/o contraseña incorrectas");
        }

    }
    public boolean validarlogin(String email, String psw){
        //Aqui Santiago obtiene el get de la base de datos
        boolean result = false;



        return result;

    }







}
