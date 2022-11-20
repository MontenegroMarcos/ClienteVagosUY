package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividadRealizada;
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

    public Label lblfecha;
    public Label lblInicio;
    public Label lblFinal;
    @FXML
    private Button btnCancelarReserva;

    String emailEmpleado;

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


    public void setData(ModeloActividadRealizada reserva, String emailEmpleado){
        this.lblNombre.setText(String.valueOf(reserva.getActividad().getKey().getNombre()));
        this.lblPrecio.setText(String.valueOf(reserva.getActividad().getPrecio()));
        this.lblCentroDeportivo.setText(String.valueOf(reserva.getActividad().getKey().getCentroDeportivo().getNombre()));
        this.lbldireccion.setText(String.valueOf(reserva.getActividad().getKey().getCentroDeportivo().getDireccion()));
        this.lblCategorias.setText(String.valueOf(reserva.getActividad().getCategoria()));
        this.emailEmpleado = emailEmpleado;
        List<Long> idImagenes = obtenerIdImagenes(reserva.getActividad().getKey().getCentroDeportivo().getEmail(), reserva.getActividad().getKey().getNombre());
        this.imageview.setImage(obtenerimagen(idImagenes.get(idImagenes.size() - 1)));
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
