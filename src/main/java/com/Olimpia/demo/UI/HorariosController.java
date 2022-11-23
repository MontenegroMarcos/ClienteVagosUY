package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class HorariosController {

    VerHorariosActividadesController controller;

    @FXML
    private Stage estage;
    @FXML
    private Label diaActividad;
    @FXML
    private Label horaInicio;
    @FXML
    private Label horaFin;
    @FXML
    private Label cupos;
    private Integer cuposBD;

    private String emailCentro;
    private String nombreActividad;
    private String emailEmpleado;

    public void setData(List horas, String emailCentro, String nombreActividad, String emailEmpleado, Stage stage, VerHorariosActividadesController verHorariosActividadesController) {
        this.estage = stage;
        this.controller = verHorariosActividadesController;


        this.diaActividad.setText(String.valueOf(horas.get(0)));
        this.horaInicio.setText(String.valueOf(horas.get(1)));
        this.horaFin.setText(String.valueOf(horas.get(2)));
        this.cuposBD = (Integer)horas.get(3);
        this.cupos.setText(getCupos(this.cuposBD));
        this.emailCentro=emailCentro;
        this.nombreActividad=nombreActividad;
        this.emailEmpleado=emailEmpleado;
    }

    private String getCupos(Integer cupos){
        String retorno;
        if(cupos.equals(-1)){
            retorno="Ilimitados";
        }else{
            retorno=String.valueOf(cupos);
        }
        return retorno;
    }

    public void hacerReserva(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hacerReserva.fxml"));
            Parent root = fxmlLoader.load();
            HacerReservaController controlador = fxmlLoader.getController();

            /* String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                anchorpane.setId("pane");
                */
            Scene scene = new Scene(root);

            String css = this.getClass().getResource("inicioestilo.css").toExternalForm();
            scene.getStylesheets().add(css);


            Stage stage = new Stage();
            stage.setScene(scene);
            controlador.init(diaActividad.getText(),horaInicio.getText(),horaFin.getText(),this.cuposBD,this.emailCentro,this.nombreActividad,this.emailEmpleado,this.controller,stage);
           controller.ocultar();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
