package com.Olimpia.demo.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Inicio extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        InicioController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("GymAPP");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }



}
