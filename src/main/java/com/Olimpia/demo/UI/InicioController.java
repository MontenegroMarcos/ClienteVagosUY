package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import javafx.scene.control.TextField;

import java.awt.*;

import java.io.File;
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
    private ImageView imageview;

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
        CentroDeportivoController controlador = fxmlLoader.getController();
        controlador.init(this.campoEmail.getText());
        stage.show();
        this.estage.close();
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
        Parent root = fxmlLoader.load(UsuarioAdminController.class.getResourceAsStream("usuariosFinales.fxml"));
        UsuariosFinalesController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        String css = this.getClass().getResource("usuariosfinalesEstilo.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);

        System.out.println(campoEmail.getText());
        controller.init(campoEmail.getText(), stage, this);
        stage.show();
        this.estage.close();
    }


    public void setStage(Stage stage) {
        estage = stage;
        //guarda info pantalla
    }

    @FXML
    public void mostrarContaseña(ActionEvent event) {
        if (mostrarOcultar.isSelected()) {
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
        File file1 = new File("src/usuarioVagosUY.png");
        Image imagen1 = new Image(file1.toURI().toString());
        this.imageview.setImage(imagen1);
    }

    public void mostrarContasenia(javafx.event.ActionEvent actionEvent) {
    }

    @FXML
    public void entrarUsuariofinalLogin() {


    }

    public void Login() {
        String email = campoEmail.getText();
        String psw = campoPassword.getText();
        try {
            ObjectMapper mapper = new ObjectMapper();
            ModeloUsuario usuario = new ModeloUsuario(email, psw);
            String jsonString = mapper.writeValueAsString(usuario);
            HttpResponse<JsonNode> response = Unirest.post("http://10.252.60.114:8080/vagouy/usuario/login")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(jsonString)
                    .asJson();
            if (response.getStatus() == 400) {
                this.alertLabel.setText("Error, email y/o contraseña incorrectos");

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(UsuarioAdminController.class.getResourceAsStream("popErrorPantalla.fxml"));

                PopErrorPantallaController controller = fxmlLoader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);

                String css = this.getClass().getResource("usuariosfinalesEstilo.css").toExternalForm();
                scene.getStylesheets().add(css);

                controller.setinit("error, correo y/o contraseña incorrectos", estage);
                controller.setStage(stage);
                stage.show();
                estage.close();

                //Login INCORRECTO
                //Abrir pantalla error
                //FIXME
            } else {
                if (response.getStatus() == 202) {
                    try {
                        ModeloUsuario usuarioValido = mapper.readValue(response.getBody().toString(), ModeloUsuario.class);
                        switch (usuarioValido.getTipoUsuario()) {
                            case 0:
                                //Abrir pantalla admin
                                entrarUsuarioAdmin();
                                break;
                            case 1:
                                //Abrir pantalla empresa
                                break;
                            case 2:
                                //Abrir pantalla centroDeportivo
                                entrarUsuarioCentroDeportivo();
                                break;
                            case 3:
                                //Abrir pantalla Empleados
                                entrarUsuarioFinal();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
