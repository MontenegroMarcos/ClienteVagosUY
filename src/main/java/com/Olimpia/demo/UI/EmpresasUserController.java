package com.Olimpia.demo.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpresasUserController implements Initializable {

    @FXML
    private ImageView imagenvista;

    @FXML
    private TextField campoemail;

    @FXML
    private Button cambiarimagen;

    Image imagen_uno = new Image(getClass().getResourceAsStream(tierra.jpg));


    @FXML
    public void cambiarImagenes() throws IOException {
       /* String email = campoemail.getText();
        InputStream is = obtenerImagen(email);
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];

        int size = 0;

        while((size = is.read(content)) != -1){
            os.write(content,0,size);
        }
        os.close();
        is.close();

        Image imagenbase = new Image("file:photo.jpg", 100, 150, true, true) {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        imagen = new ImageView((Element) image);
*/
    }

    public InputStream obtenerImagen(String email){

        return null;
    }


    @FXML
    void cambiarImagenSH(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
