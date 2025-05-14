package co.edu.uniquindio.poo.notificacionesseguimiento1.viewController;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.notificacionesseguimiento1.App;

import co.edu.uniquindio.poo.notificacionesseguimiento1.controller.MensajeriaController;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MensajeriaVC {

    private App app;
    private ObservableList<Mensaje> listaMensajes = FXCollections.observableArrayList();
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    private Mensaje selectedMensaje;
    private Usuario selectedUsuario;
    MensajeriaController mensajeriaController;
    private double x = 0,y = 0;



    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void animarTextField(TextField textField) {
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                // Si el TextField gana el foco, se expande
                Timeline expandir = new Timeline(
                        new KeyFrame(Duration.millis(400),
                                new KeyValue(textField.prefWidthProperty(), 200)) // Expande hasta 250px
                );
                expandir.play();
            } else {
                // Si el TextField pierde el foco, vuelve a su tamaño normal
                Timeline reducir = new Timeline(
                        new KeyFrame(Duration.millis(400),
                                new KeyValue(textField.prefWidthProperty(), 150)) // Reduce a 150px
                );
                reducir.play();
            }
        });
    }


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button btt_Eliminar;

        @FXML
        private TableColumn<Usuario, String> tbc_TipoUsuario;

        @FXML
        private TableView<Usuario> tb_Usuario;

        @FXML
        private TextArea txt_Mensaje;

        @FXML
        private Button btt_Agregar;

        @FXML
        private TableView<Mensaje> tb_Mensajeria;

        @FXML
        private AnchorPane sideBar;

        @FXML
        private TextField txt_codigoMensaje;

        @FXML
        private TableColumn<Mensaje, String> tbc_Usuario;

        @FXML
        private TableColumn<Usuario, String> tbc_CanalUsuario;

        @FXML
        private TableColumn<Mensaje, String> tbc_CodigoMensaje;

        @FXML
        private TableColumn<Mensaje, String> tbc_mensaje;

        @FXML
        private TableColumn<Usuario, String> tbc_NombreUsuario;

        @FXML
        private Button Volver;

        @FXML
        void Volver(ActionEvent event) {
            try {
                app.openViewPrincipal();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al abrir la vista principal");
            }
        }



        @FXML
        void AgregarMensaje(ActionEvent event) {
            agregarMensaje();
            mostrarMensaje();
        }

        @FXML
        void EliminarMensaje(ActionEvent event) {
            eliminarMensaje();
        }



        @FXML
        void initialize() {
            assert btt_Eliminar != null : "fx:id=\"btt_Eliminar\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_TipoUsuario != null : "fx:id=\"tbc_TipoUsuario\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tb_Usuario != null : "fx:id=\"tb_Usuario\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert txt_Mensaje != null : "fx:id=\"txt_Mensaje\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert btt_Agregar != null : "fx:id=\"btt_Agregar\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tb_Mensajeria != null : "fx:id=\"tb_Mensajeria\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert sideBar != null : "fx:id=\"sideBar\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert txt_codigoMensaje != null : "fx:id=\"txt_codigoMensaje\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_Usuario != null : "fx:id=\"tbc_Usuario\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_CanalUsuario != null : "fx:id=\"tbc_CanalUsuario\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_CodigoMensaje != null : "fx:id=\"tbc_CodigoMensaje\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_mensaje != null : "fx:id=\"tbc_mensaje\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert tbc_NombreUsuario != null : "fx:id=\"tbc_NombreUsuario\" was not injected: check your FXML file 'Mensaje.fxml'.";
            assert Volver != null : "fx:id=\"Volver\" was not injected: check your FXML file 'Mensaje.fxml'.";
            mensajeriaController = new MensajeriaController(App.centralMensajeria);
            obtenerMensaje();
            obtenerUsuario();
            initView();
        }

    private void obtenerUsuario() {
        if (listaUsuarios == null) {
            listaUsuarios = FXCollections.observableArrayList();
        }

        Collection<Usuario> usuarios = mensajeriaController.obtenerListaUsuarios();
        if (usuarios != null) {
            listaUsuarios.setAll(usuarios);
        } else {
            System.out.println("No se encontraron Usuarios.");
        }
    }


    /**
     *Metodo que permite obtener los datos del Mensaje
     */
    private void obtenerMensaje() {
        if (mensajeriaController != null) {
            listaMensajes.addAll(mensajeriaController.obtenerListaMensajes());
        } else {
            System.err.println("MensajeriaController no está inicializado.");
        }
    }


    /**
     *Metodo que inicializa la vista en javaFX
     */
    private void initView() {
        initDataBinding();
        tb_Mensajeria.getItems().clear();
        tb_Mensajeria.setItems(listaMensajes);
        tb_Usuario.setItems(listaUsuarios);
        listenerSelectionMensaje();
        listenerSelectionUsuario();
    }

    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbc_Usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario().getNombre()));
        tbc_CodigoMensaje.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbc_mensaje.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMensaje()));
        tbc_TipoUsuario.setCellValueFactory(celldata -> {
            Tipo tipo = celldata.getValue().getTipo();
            String TamanioString = (tipo != null) ? tipo.toString() : "Sin Tipo";
            return new SimpleStringProperty(TamanioString);
        });
        tbc_NombreUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbc_CanalUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCanalSeleccionado().getNombreCanal()));

    }

    /**
     *Metodo que escucha los cambios en la selecion de un mensaje y actualiza la variable selectedMensaje
     */
    private void listenerSelectionMensaje() {
        tb_Mensajeria.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedMensaje = newSelection;
            mostrarInformacionMensaje(selectedMensaje);
        });
    }

    /**
     *Metodo que detecta cuando el usuario selecciona un mensaje  en la tabla
     */
    private void listenerSelectionUsuario() {
        tb_Usuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedUsuario = newSelection;
        });
    }



    /**
     *Metodo que muestra la información del paciente sleccionado en los campos de la interfaz
     * @param mensaje
     */
    private void mostrarInformacionMensaje(Mensaje mensaje) {
        if (mensaje != null) {
            txt_Mensaje.setText(mensaje.getMensaje());
            tbc_CodigoMensaje.setText(mensaje.getCodigo());

            String resultado = mensajeriaController.generarMensaje(mensaje, mensaje.getMensaje());


        }
    }


    /**
     *Metodo que agrega un mensaje
     */
    private void agregarMensaje() {
        Mensaje mensaje = buildMensaje();
        String resultadoFiltro = aplicarFiltros(mensaje);

        if (resultadoFiltro != null) {
            // Mostrar mensaje de advertencia
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText(resultadoFiltro);
            alerta.showAndWait();

            txt_Mensaje.setText(resultadoFiltro);
            return;
        }

        if (mensajeriaController.agregarMensaje(mensaje)) {
            listaMensajes.add(mensaje);
            limpiarCampos();
        }
    }


    /**
     *Metodo que crea una instancia de mensaje con los datos ingresados en la interfaz
     * @return
     */
    private Mensaje buildMensaje() {
        Mensaje mensaje = new Mensaje(
                txt_codigoMensaje.getText(),
                selectedUsuario,
                txt_Mensaje.getText()

        );
        return mensaje;

    }


    private void eliminarMensaje() {
        if (mensajeriaController.eliminarMensaje(String.valueOf(selectedMensaje))) {
            listaMensajes.remove(selectedMensaje);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void mostrarMensaje() {
        if (listaMensajes != null && !listaMensajes.isEmpty()) {
            Mensaje ultimoMensaje = listaMensajes.get(listaMensajes.size() - 1);
            String texto = ultimoMensaje.getMensaje();

            String resultado = mensajeriaController.generarMensaje(ultimoMensaje, texto);

            if (resultado.equals("El mensaje no puede ser vacio") || resultado.equals("El usuario esta bloqueado")) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText(resultado);
                alerta.showAndWait();
            }

            txt_Mensaje.setText(resultado);
        } else {
            tbc_mensaje.setText("No hay mensajes para mostrar.");
        }

    }

    private String aplicarFiltros(Mensaje mensaje) {
        Filtro filtro1 = new MensajeVacio();
        Filtro filtro2 = new UsuarioBloqueado();
        filtro1.setNext(filtro2);

        return filtro1.handle(mensaje);
    }




    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tb_Usuario.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo quue limpia los campos del mensaje seleccionado
     */
    private void limpiarCampos() {
        txt_Mensaje.clear();
        txt_codigoMensaje.clear();
        tb_Usuario.getSelectionModel().clearSelection();
    }

    /**
     *metodo que seta APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;

    }
    }


