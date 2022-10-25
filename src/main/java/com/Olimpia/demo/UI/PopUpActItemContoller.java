package com.Olimpia.demo.UI;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class PopUpActItemContoller {

    @FXML
    private Button btnImagenSiguiente;

    @FXML
    private Button btnimagenAnterior;



    @FXML
    private ImageView imageView;

    @FXML
    private Text nombreActividad;

    int indiceIMG = 0;

    List<Image> listaImagenes;



    public void init(String nombreACT, List<Image> imagenes){
        this.nombreActividad.setText(nombreACT);
        this.imageView.setImage(imagenes.get(0));
        this.listaImagenes = imagenes;

    }

    public void siguienteImagen(){

        if(indiceIMG == listaImagenes.size()){
            indiceIMG = 0;
            this.imageView.setImage(listaImagenes.get(0));

        } else {
            this.imageView.setImage(listaImagenes.get(indiceIMG + 1));
            indiceIMG++;
        }

    }

    public void anterioreImagen(){

        if(indiceIMG == 0){
            indiceIMG = listaImagenes.size();
            this.imageView.setImage(listaImagenes.get(indiceIMG));

        } else {
            this.imageView.setImage(listaImagenes.get(indiceIMG - 1));
            indiceIMG--;
        }

    }

}

