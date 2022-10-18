package com.Olimpia.demo.UI;


import com.Olimpia.demo.modelo.ModeloActividad;
import com.Olimpia.demo.modelo.ModeloEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import kong.unirest.Unirest;


//import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsuariosFinalesController implements Initializable {

    private InicioController controllerInicio;
    private Stage estage;

    @FXML
    private Text textoUsuario;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField buscartxtfield;
    @FXML
    private GridPane gridpane;
    @FXML
    private ScrollPane scrollpane;

    private String texto;

    private List<ModeloActividad> itemAct = new ArrayList<>();

    private List<ModeloActividad> itemActAux = new ArrayList<>();

    private ObservableList<ModeloActividad> listaObservableAct;

    private List<ModeloActividad> getItemAct(){
        List<ModeloActividad> itemAct = new ArrayList<>();
        ModeloActividad actividad = null;

        List<String> actividad1Cat = new ArrayList<>();
        actividad1Cat.add("Libre");
        actividad1Cat.add("Maquina");
        actividad1Cat.add("Compartido");

        List<String> actividad2Cat = new ArrayList<>();
        actividad1Cat.add("Agua");
        actividad1Cat.add("Reserva");
        actividad1Cat.add("Compartido");


        List<String> actividad3Cat = new ArrayList<>();
        actividad1Cat.add("Futbol");
        actividad1Cat.add("Reserva");
        actividad1Cat.add("Compartido");


        ModeloActividad actividad1 = new ModeloActividad("Gym Star uso libre",actividad1Cat,null,20.3f,"Lo de Carlo", "por alla , cerca de aqui y rn la esquina de al lado");
        ModeloActividad actividad2 = new ModeloActividad("Piscina Artigas",actividad2Cat,null,100.3f,"Lo de Carlo", "Universo 616");
        ModeloActividad actividad3 = new ModeloActividad("Fuvol",actividad3Cat,null,20.3f,"Lo de Carlo", "En todos lados");


        itemAct.add(actividad1);
        itemAct.add(actividad2);
        itemAct.add(actividad3);

        return itemAct;
    }



    public void init(String text, Stage stage, InicioController inicioController) {
        this.textoUsuario.setText(text);
        this.controllerInicio = inicioController;
        this.estage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemAct.addAll(getItemAct());
        int filas = 0;

        try {

            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();

                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i),"src/Charcoal.jpg");

                gridpane.add(anchorpane,0,filas++);
                GridPane.setMargin(anchorpane,new Insets(10));

            }
        } catch (Exception e){

        }
    }

    public void filtrarNombre(KeyEvent key){

        String filtroNombreAct = this.buscartxtfield.getText();
        int filas = 0;


        if(filtroNombreAct.isEmpty()){
            this.gridpane.getChildren().clear();

            itemAct.addAll(getItemAct());


            try {

                for (int i = 0; i < itemAct.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ActividadesItemUserController controlador = fxmlLoader.getController();
                    controlador.setData(itemAct.get(i),"src/Charcoal.jpg");

                    gridpane.add(anchorpane,0,filas++);
                    GridPane.setMargin(anchorpane,new Insets(10));

                }
            } catch (Exception e){

            }
        } else {

            this.gridpane.getChildren().clear();
            this.itemActAux.clear();

            for (ModeloActividad activity:this.itemAct) {
                if (activity.getNombreActividad().toLowerCase().contains(filtroNombreAct.toLowerCase())){
                        this.itemActAux.add(activity);
                }
            }
            try {
                for (int i = 0; i < itemActAux.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ActividadesItemUserController controlador = fxmlLoader.getController();
                    controlador.setData(itemActAux.get(i),"src/Charcoal.jpg");

                    gridpane.add(anchorpane,0,filas++);
                    GridPane.setMargin(anchorpane,new Insets(10));

                }
            } catch (Exception e){

            }


        }

    }

    @FXML
    protected ObservableList<ModeloActividad> obtenerActividades(){
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://10.252.60.160:8080/vagouy/actividades").asString().getBody();
        List<ModeloActividad> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<ModeloActividad>>() {});
        }catch(Exception e){
            e.printStackTrace();
        }
        ObservableList<ModeloActividad> retorno = FXCollections.observableList(actividad);
        System.out.println(retorno);
        return retorno;
    }


    @FXML
    public void listarActividades(ObservableList<ModeloActividad> listaActividades) throws IOException {
        int filas = 0;

        this.listaObservableAct = listaActividades;


        /*for (ModeloActividad activity:listaActividades) {

        }*/

        for (int i = 0; i < listaActividades.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();

            ActividadesItemUserController controlador = fxmlLoader.getController();
            controlador.setData(listaObservableAct.get(i),"src/Charcoal.jpg");

            gridpane.add(anchorpane,0,filas++);
            GridPane.setMargin(anchorpane,new Insets(10));


        }

        /*this.gridpane.getChildren().clear();
        this.itemActAux.clear();

        for (ModeloActividad activity:this.itemAct) {
            if (activity.getNombreActividad().toLowerCase().contains(filtroNombreAct.toLowerCase())){
                this.itemActAux.add(activity);
            }
        }
        try {
            for (int i = 0; i < itemActAux.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();

                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemActAux.get(i),"src/Charcoal.jpg");

                gridpane.add(anchorpane,0,filas++);
                GridPane.setMargin(anchorpane,new Insets(10));*/
    }

}
