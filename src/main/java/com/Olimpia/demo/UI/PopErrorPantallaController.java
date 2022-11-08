package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopErrorPantallaController implements Initializable {

    @FXML
    private Button btnaceptar;

    @FXML
    private ImageView imageview;

    @FXML
    private Text textalert;

    @FXML
    private Stage estage;

    public void setinit(String texto, Stage stage){
        this.textalert.setText(texto);
        this.estage = stage;
        File file = new File("src/problemasTecnicos.jpg");
        Image imagen = new Image(file.toURI().toString());
        this.imageview.setImage(imagen);

    }

    public void setStage(Stage stage) {
        estage = stage;
        //guarda info pantalla
    }

    @FXML
    public void volverAceptar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        InicioController controller = fxmlLoader.getController();
        String css = this.getClass().getResource("inicioestilo.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        controller.setStage(stage);
        stage.setTitle("GymAPP");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        this.estage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/problemasTecnicos.jpg");
        Image imagen = new Image(file.toURI().toString());
        this.imageview.setImage(imagen);
    }
}
