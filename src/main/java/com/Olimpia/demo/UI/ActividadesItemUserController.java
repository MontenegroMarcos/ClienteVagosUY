package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividad;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.File;

public class ActividadesItemUserController {

    @FXML
    private Label categorias;

    @FXML
    private Label direccionActividad;

    @FXML
    private Label fechayhora;

    @FXML
    private ImageView imagenActividad;

    @FXML
    private Label nombreCentroDeportivo;

    @FXML
    private Label nombreactividad;

    @FXML
    private Label precioactividad;

    private ModeloActividad actividad;

    private byte [] bitArray;

    public void setData(ModeloActividad activity,String t) {
        this.actividad = activity;
        this.nombreactividad.setText(activity.getNombreActividad());
        this.precioactividad.setText(String.valueOf(activity.getPrecio()));
        this.nombreCentroDeportivo.setText(activity.getNombreCentrodeActividad());
        this.fechayhora.setText(String.valueOf(activity.getFechayhora()));
        this.direccionActividad.setText(activity.getDireccionCentro());
        this.categorias.setText(activity.getCategorias().toString());




    }
}
