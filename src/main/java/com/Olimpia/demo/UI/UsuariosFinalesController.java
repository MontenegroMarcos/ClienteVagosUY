package com.Olimpia.demo.UI;


import com.Olimpia.demo.modelo.ModeloActividad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kong.unirest.Unirest;


//import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuariosFinalesController implements Initializable {

    public Pagination paginador;
    public Button btncerrarsesion;
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
    @FXML
    private ComboBox<String> comboBox;

    private String texto;

    private List<List> itemAct = new ArrayList<>();

    private List<ModeloActividad> itemActAux = new ArrayList<>();

    private ObservableList<ModeloActividad> listaObservableAct;


    public void init(String text, Stage stage, InicioController inicioController) {
        this.textoUsuario.setText(text);
        this.controllerInicio = inicioController;
        this.estage = stage;
    }

    @FXML
    public void cerrarsesion() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(InicioController.class.getResourceAsStream("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        InicioController controlador = fxmlLoader.getController();
        String css = this.getClass().getResource("inicioestilo.css").toExternalForm();
        scene.getStylesheets().add(css);
        controlador.setStage(stage);
        stage.setTitle("GymAPP");
        stage.show();
        this.estage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.gridpane.getChildren().clear();
        this.itemAct = obtenerActividades();


        try {
            int filas = 0;
            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                //anchorpane.setId("pane");
                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i), textoUsuario.getText());

                this.gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));


            }
        } catch (Exception e) {

        }

        ObservableList<String> lista = FXCollections.observableArrayList("Todas", "Futbol", "Basketball", "Tenis", "Otros");
        this.comboBox.setItems(lista);




    }

    private List<List> obtenerActividades() {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/Todas").asString().getBody();
        List<List> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    public void filtrarPorCategoria() {
        String valor = comboBox.getValue();
        System.out.println(valor);
        this.gridpane.getChildren().clear();
        itemAct = obtenerActividadesPorCategoria(valor);
        int filas = 0;

        try {

            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                anchorpane.setId("pane");
                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i), textoUsuario.getText());

                gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));

            }
        } catch (Exception e) {

        }
    }

    public List<List> obtenerActividadesPorCategoria(String categoria) {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/" + categoria).asString().getBody();
        List<List> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    public void buscarPorCentroDeportivo() {
        String nombre = buscartxtfield.getText();
        System.out.println(nombre);
        this.gridpane.getChildren().clear();
        itemAct = obtenerActividadesPorCentroDeportivo(nombre);
        int filas = 0;

        try {

            for (int i = 0; i < itemAct.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                anchorpane.getStylesheets().add(css);
                anchorpane.setId("pane");
                ActividadesItemUserController controlador = fxmlLoader.getController();
                controlador.setData(itemAct.get(i), textoUsuario.getText());

                gridpane.add(anchorpane, 0, filas++);
                GridPane.setMargin(anchorpane, new Insets(10));

            }
        } catch (Exception e) {

        }
    }

    public List<List> obtenerActividadesPorCentroDeportivo(String cdNombre) {
        ObjectMapper mapper = new ObjectMapper();
        String actividades = Unirest.get("http://localhost:8080/vagouy/Actividades/centro/" + cdNombre).asString().getBody();
        List<List> actividad = null;
        try {
            actividad = mapper.readValue(actividades, new TypeReference<List<List>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }


    @FXML
    public void misReservas() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(UsuarioAdminController.class.getResourceAsStream("misReservasUsuarioFinal.fxml"));

        MisReservasUsuarioFinalController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);

        String css = this.getClass().getResource("usuariosfinalesEstilo.css").toExternalForm();
        scene.getStylesheets().add(css);

        controller.setinit(this.textoUsuario.getText(), estage);
        controller.setStage(stage);
        stage.show();
        estage.close();
    }


   /* public void filtrarNombre(KeyEvent key){

        String filtroNombreAct = this.buscartxtfield.getText();
        int filas = 0;


        if(filtroNombreAct.isEmpty()){
            this.gridpane.getChildren().clear();

            itemAct=obtenerActividades();


            try {

                for (int i = 0; i < itemAct.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ActividadesItemUserController controlador = fxmlLoader.getController();
                    controlador.setData(itemAct.get(i));

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
                    controlador.setData(itemActAux.get(i));

                    gridpane.add(anchorpane,0,filas++);
                    GridPane.setMargin(anchorpane,new Insets(10));

                }
            } catch (Exception e){

            }


        }

    }

    /*@FXML
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


        for (ModeloActividad activity:listaActividades) {

        }

        for (int i = 0; i < listaActividades.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();

            ActividadesItemUserController controlador = fxmlLoader.getController();
            controlador.setData(listaObservableAct.get(i));

            gridpane.add(anchorpane,0,filas++);
            GridPane.setMargin(anchorpane,new Insets(10));


        }

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
    }*/

     /*AtomicInteger filas = new AtomicInteger(0);
        //paginador.setPageCount(itemAct.size()/5);

        paginador.setPageFactory((pageIndex) -> {
            try {

                for (int i = pageIndex; i < pageIndex + 5; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ActividadesItemUser.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();
                    String css = this.getClass().getResource("actividaditemuserEstilo.css").toExternalForm();
                    anchorpane.getStylesheets().add(css);
                    //anchorpane.setId("pane");

                    //scene.getStylesheets().add(css);
                    ActividadesItemUserController controlador = fxmlLoader.getController();
                    controlador.setData(itemAct.get(i),textoUsuario.getText());

                    gridpane.add(anchorpane,0, filas.getAndIncrement());

                    GridPane.setMargin(anchorpane,new Insets(10));

                }
            } catch (Exception e){

            }

            return this.scrollpane;
        });

         */
}
