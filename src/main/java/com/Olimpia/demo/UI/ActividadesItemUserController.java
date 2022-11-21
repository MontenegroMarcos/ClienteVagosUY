package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.ModeloActividad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import kong.unirest.Unirest;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActividadesItemUserController implements Initializable {

    @FXML
    private Label categorias;

    @FXML
    private HBox horizontalbox;

    @FXML
    private Label direccionActividad;

    @FXML
    private ImageView imagenActividad;

    @FXML
    private Label nombreCentroDeportivo;

    @FXML
    private Label nombreactividad;

    @FXML
    private Label precioactividad;

    @FXML
    private Button btnVerHorarios;

    private String emailCD;

    private ModeloActividad actividad;

    private String emailEmpleado;


    public void setData(List actCd,String emailEmpleado) {
        this.nombreactividad.setText(String.valueOf(actCd.get(0)));
        this.precioactividad.setText(String.valueOf(actCd.get(2)));
        this.nombreCentroDeportivo.setText(String.valueOf(actCd.get(3)));
        this.direccionActividad.setText(String.valueOf(actCd.get(4)));
        this.categorias.setText(String.valueOf(actCd.get(1)));
        this.emailCD = String.valueOf(actCd.get(5));
        List<Long> idImagenes = obtenerIdImagenes(this.emailCD, String.valueOf(actCd.get(0)));
        this.imagenActividad.setImage(obtenerimagen(idImagenes.get(idImagenes.size() - 1)));
        this.emailEmpleado=emailEmpleado;
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

    public void pantallaVerHorarios() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("VerHorariosActividades.fxml"));
            Parent root = fxmlLoader.load();
            VerHorariosActividadesController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            controller.setConfig(stage,this);

            String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
            scene.getStylesheets().add(css);


            stage.setScene(scene);
            controller.init(this.nombreactividad.getText(), this.nombreCentroDeportivo.getText(), this.emailCD,this.emailEmpleado,stage,this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
        this.horizontalbox = new HBox();
        this.horizontalbox.getStylesheets().add(css);
        this.horizontalbox.setId("horizontalbox");
    }


}
