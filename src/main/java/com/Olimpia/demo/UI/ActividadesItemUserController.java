package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividad;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kong.unirest.Unirest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

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


    public void setData(List actCd) {
        this.nombreactividad.setText(String.valueOf(actCd.get(0)));
        this.precioactividad.setText(String.valueOf(actCd.get(5)));
        this.nombreCentroDeportivo.setText(String.valueOf(actCd.get(6)));
        this.fechayhora.setText(String.valueOf(actCd.get(2)));
        this.direccionActividad.setText(String.valueOf(actCd.get(7)));
        this.categorias.setText(String.valueOf(actCd.get(1)));
        this.imagenActividad.setImage(obtenerimagen("nacionalnacional.png"));
    }

    private Image obtenerimagen(String name){
        byte[] imagen = Unirest.get("http://10.252.60.160:8080/Imagen/"+name).asBytes().getBody();
        ByteArrayInputStream bytearray = new ByteArrayInputStream(imagen);
        return new Image(bytearray);
    }
}
