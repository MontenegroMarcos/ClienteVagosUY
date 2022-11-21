package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividadRealizada;
import com.Olimpia.demo.modelo.ModeloUsuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ElementoReservasUserController {

    private ModeloActividadRealizada reserva;

    @FXML
    public Label lblfecha;

    @FXML
    public Label lblInicio;

    @FXML
    public Label lblFinal;

    @FXML
    private Button btnCancelarReserva;

    private String emailEmpleado;

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
        this.reserva = reserva;
        this.lblNombre.setText(String.valueOf(reserva.getActividad().getKey().getNombre()));
        this.lblfecha.setText(fechaStr(reserva.getFecha()));
        this.lblInicio.setText(reserva.getHorarios().getKey().getHoraInicio());
        this.lblFinal.setText(reserva.getHorarios().getKey().getHoraFin());
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

    private String fechaStr(Date date){
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        return format1.format(date);
    }

    public void cancelarReserva(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String strReserva = mapper.writeValueAsString(this.reserva);
            HttpResponse<JsonNode> responseUsr = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/reserva/cancelarReserva/"+this.emailEmpleado)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(strReserva)
                    .asJson();
            System.out.println(responseUsr.getStatus());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
