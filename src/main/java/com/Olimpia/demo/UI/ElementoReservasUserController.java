package com.Olimpia.demo.UI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kong.unirest.Unirest;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ElementoReservasUserController {

    @FXML
    private Button btnCancelarReserva;

    String emailCD;

    @FXML
    private ImageView imageview;

    @FXML
    private Label lblCategorias;

    @FXML
    private Label lblCentroDeportivo;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPrecio;

    @FXML
    private Label lbldireccion;


    void setData(List element, String nombreUser){
        this.lblNombre.setText(String.valueOf(element.get(0)));
        this.lblPrecio.setText(String.valueOf(element.get(2)));
        this.lblCentroDeportivo.setText(String.valueOf(element.get(3)));
        this.lbldireccion.setText(String.valueOf(element.get(4)));
        this.lblCategorias.setText(String.valueOf(element.get(1)));
        this.emailCD = nombreUser;
        List<Long> idImagenes = obtenerIdImagenes(this.emailCD, String.valueOf(element.get(0)));
        this.imageview.setImage(obtenerimagen(idImagenes.get(idImagenes.size() - 1)));
        //this.emailEmpleado=emailEmpleado;

    }

    private Image obtenerimagen(Long id) {
        byte[] imagen = Unirest.get("http://localhost:8080/Imagen/" + id).asBytes().getBody();
        ByteArrayInputStream bytearray = new ByteArrayInputStream(imagen);
        return new Image(bytearray);
    }

    private List<Long> obtenerIdImagenes(String email_centro, String nombre) {
        ObjectMapper mapper = new ObjectMapper();
        String imagenesJson = Unirest.get("http://localhost:8080/vagouy/Actividades/imagen/" + email_centro + "/" + nombre).asString().getBody();
        List<Long> imagenes = null;
        try {
            imagenes = mapper.readValue(imagenesJson, new TypeReference<List<Long>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagenes;
    }
}
