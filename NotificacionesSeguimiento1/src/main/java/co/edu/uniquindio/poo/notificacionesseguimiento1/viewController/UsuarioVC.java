package co.edu.uniquindio.poo.notificacionesseguimiento1.viewController;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Tipo;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Tipo.*;

import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Canal;
import co.edu.uniquindio.poo.notificacionesseguimiento1.App;
import co.edu.uniquindio.poo.notificacionesseguimiento1.controller.UsuarioController;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Usuario;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UsuarioVC {

    private App app;
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    private ObservableList<Canal> listaCanales = FXCollections.observableArrayList();
    private Canal selectedCanal;
    private Usuario selectedUsuario;
    UsuarioController usuarioController;
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
    private TableView<Canal> tb_canal;

    @FXML
    private TableColumn<Usuario, String> tbc_CanalSeleccionado;

    @FXML
    private TableColumn<Usuario, String> tbc_TipoUsuario;

    @FXML
    private CheckBox ckbx_Bloqueado;

    @FXML
    private TableColumn<Canal, String> tbc_Canal;

    @FXML
    private TableView<Usuario> tb_Usuario;

    @FXML
    private TextField txt_Email;

    @FXML
    private Button btt_Agregar;

    @FXML
    private Button btn_Actualizar;

    @FXML
    private ComboBox<Tipo> cmbx_Tipo;

    @FXML
    private TableColumn<Usuario, String> tbc_EmailUsuario;

    @FXML
    private AnchorPane sideBar;

    @FXML
    private TextField txt_nombreUsuario;

    @FXML
    private TableColumn<Canal, String> tbc_codigo;

    @FXML
    private TableColumn<Usuario, Boolean> tbc_Bloqueado;

    @FXML
    private TableColumn<Usuario, String> tbc_nombreUsuario;

    @FXML
    private Button btt_Limpiar;

    @FXML
    private Button Volver;


    @FXML
    void Volver(ActionEvent event) {
        try {
        app.openViewPrincipal();
    }catch (Exception e){
        e.printStackTrace();
        System.err.println("Error al abrir la vista principal");
    }
    }

    @FXML
    void AgregarUsuario(ActionEvent event) {
        agregarUsuario();

    }

    @FXML
    void EliminarUsuario(ActionEvent event) {
        eliminarUsuario();
    }

    @FXML
    void LimpiarUsuario(ActionEvent event) {
        limpiarCampos();
    }


    @FXML
    void ActualizarUsuario(ActionEvent event) {
        actualizarUsuario();
    }


    @FXML
    void initialize() {
        assert btt_Eliminar != null : "fx:id=\"btt_Eliminar\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tb_canal != null : "fx:id=\"tb_canal\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_CanalSeleccionado != null : "fx:id=\"tbc_CanalSeleccionado\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_TipoUsuario != null : "fx:id=\"tbc_TipoUsuario\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert ckbx_Bloqueado != null : "fx:id=\"ckbx_Bloqueado\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_Canal != null : "fx:id=\"tbc_Canal\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tb_Usuario != null : "fx:id=\"tb_Usuario\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert txt_Email != null : "fx:id=\"txt_Email\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert btt_Agregar != null : "fx:id=\"btt_Agregar\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert btn_Actualizar != null : "fx:id=\"btn_Actualizar\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert cmbx_Tipo != null : "fx:id=\"cmbx_Tipo\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_EmailUsuario != null : "fx:id=\"tbc_EmailUsuario\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert sideBar != null : "fx:id=\"sideBar\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert txt_nombreUsuario != null : "fx:id=\"txt_nombreUsuario\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_codigo != null : "fx:id=\"tbc_codigo\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_Bloqueado != null : "fx:id=\"tbc_Bloqueado\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert tbc_nombreUsuario != null : "fx:id=\"tbc_nombreUsuario\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert btt_Limpiar != null : "fx:id=\"btt_Limpiar\" was not injected: check your FXML file 'Usuario.fxml'.";
        assert Volver != null : "fx:id=\"Volver\" was not injected: check your FXML file 'Usuario.fxml'.";

        usuarioController = new UsuarioController(App.centralMensajeria);
        obtenerUsuario();
        obtenerCanal();
        initView();
    }

    private void obtenerCanal() {
        if (listaCanales == null) {
            listaCanales = FXCollections.observableArrayList();
        }

        Collection<Canal> canales = usuarioController.obtenerListaCanales();
        if (canales != null) {
            listaCanales.setAll(canales);
        } else {
            System.out.println("No se encontraron canales.");
        }
    }


    /**
     *Metodo que permite obtener los datos de Usuario
     */
    private void obtenerUsuario() {
        if (usuarioController != null) {
            listaUsuarios.addAll(usuarioController.obtenerListaUsuarios());
        } else {
            System.err.println("Usuario no está inicializado.");
        }
    }


    /**
     *Metodo que inicializa la vista en javaFX
     */
    private void initView() {
        initDataBinding();
        tb_Usuario.getItems().clear();
        tb_Usuario.setItems(listaUsuarios);
        tb_canal.setItems(listaCanales);
        listenerSelectionCanal();
        listenerSelectionUsuario();
    }

    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbc_nombreUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbc_EmailUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tbc_Bloqueado.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isBlocked()));
        tbc_TipoUsuario.setCellValueFactory(celldata -> {
            Usuario usuario = celldata.getValue();
            Tipo tipo = usuario.getTipo();
            return new SimpleStringProperty(tipo != null ? tipo.name() : "Sin Tipo");
        });

        tbc_CanalSeleccionado.setCellValueFactory(cellData -> {Canal seleccionado = cellData.getValue().getCanalSeleccionado();
            String nombreCanal = (seleccionado != null) ? seleccionado.getNombreCanal() : "Sin Preferencia";
            return new SimpleStringProperty(nombreCanal);});
        tbc_Canal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbc_Canal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCanal()));
    }


    private void listenerSelectionUsuario() {
        tb_Usuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedUsuario = newSelection;
            mostrarInformacionUsuario(selectedUsuario);
        });
    }


    private void listenerSelectionCanal() {
        tb_canal.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCanal = newSelection;
        });
    }




    private void mostrarInformacionUsuario(Usuario Usuario) {
        if (Usuario != null) {
            txt_nombreUsuario.setText(Usuario.getEmail());
            txt_Email.setText(Usuario.getNombre());
            cmbx_Tipo.getSelectionModel().select(Usuario.getTipo());
            ckbx_Bloqueado.setSelected(Usuario.isBlocked());
        }
    }

    /**
     *Metodo que agrega un Usuario
     */
    private void agregarUsuario() {
        Usuario usuario = buildUsuario();
        if(usuarioController.agregarUsuario(usuario)){
            listaUsuarios.add(usuario);
            limpiarCampos();
        }
    }

    /**
     *Metodo que crea una instancia de Usuario con los datos ingresados en la interfaz
     * @return
     */
    private Usuario buildUsuario() {
        Usuario usuario = new Usuario(
                txt_nombreUsuario.getText(),
                txt_Email.getText(),ckbx_Bloqueado.isSelected(),cmbx_Tipo.getValue(),selectedCanal

        );
        return usuario;

    }


    private void eliminarUsuario() {
        if (usuarioController.eliminarUsuario(String.valueOf(selectedUsuario))) {
            listaUsuarios.remove(selectedUsuario);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que actualiza la información de un Usuario seleccionado.
     * Si el Usuario se encuentra en la lista y la actualización es exitosa,
     * se reemplaza por el nuevo objeto actualizado y se refresca la tabla.
     */

    private void actualizarUsuario() {

        if (selectedUsuario != null &&
                usuarioController.actualizarUsuario(selectedUsuario.getEmail(), buildUsuario())) {

            int index = listaUsuarios.indexOf(selectedUsuario);
            if (index >= 0) {
                listaUsuarios.set(index, buildUsuario());
            }

            tb_Usuario.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tb_Usuario.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo que limpia los campos del Usuario seleccionado
     */
    private void limpiarCampos() {
        txt_nombreUsuario.clear();
        txt_Email.clear();
        ckbx_Bloqueado.setSelected(false);
        cmbx_Tipo.setValue(null);
        tb_canal.getSelectionModel().clearSelection();

    }

    /**
     *metodo que setea APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;

        ObservableList<Tipo> options = FXCollections.observableArrayList(Tipo.values());
        cmbx_Tipo.setItems((options));
    }


}