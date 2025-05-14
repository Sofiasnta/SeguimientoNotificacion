package co.edu.uniquindio.poo.notificacionesseguimiento1.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.notificacionesseguimiento1.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InicioSistemasVC implements Initializable {

    App app;

    private double x = 0,y = 0;



    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Inicializar movimiento de la ventana si es necesario
        if (sideBar1 != null) {
            sideBar1.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    stage = (Stage) newScene.getWindow();
                }
            });

            // Capturar la posición inicial al hacer clic
            sideBar1.setOnMousePressed(mouseEvent -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            // Mover la ventana al arrastrar
            sideBar1.setOnMouseDragged(mouseEvent -> {
                if (stage != null) {
                    stage.setX(mouseEvent.getScreenX() - x);
                    stage.setY(mouseEvent.getScreenY() - y);
                }
            });
        }
    }

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="btnNotificaciones"
        private Button btnNotificaciones; // Value injected by FXMLLoader

        @FXML // fx:id="btt_Otros1"
        private Button btt_Otros1; // Value injected by FXMLLoader

        @FXML // fx:id="sideBar1"
        private AnchorPane sideBar1; // Value injected by FXMLLoader

        @FXML // fx:id="btnUsuarios"
        private Button btnUsuarios; // Value injected by FXMLLoader

        @FXML
        void onAbrirVistaUsuarios(ActionEvent event) {
            try {
                app.Usuario();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @FXML
        void onAbrirVistaNotificaciones(ActionEvent event) {
            try {
                app.Mensajeria();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        void AbrirOtros(ActionEvent event) {

        }



        @FXML
        void cerrarAplicacion(ActionEvent event) {
            Platform.exit();
        }



    public void setApp(App app) {
        this.app = app;
    }

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert btnNotificaciones != null : "fx:id=\"btnNotificaciones\" was not injected: check your FXML file 'InicioSistema.fxml'.";
            assert btt_Otros1 != null : "fx:id=\"btt_Otros1\" was not injected: check your FXML file 'InicioSistema.fxml'.";
            assert sideBar1 != null : "fx:id=\"sideBar1\" was not injected: check your FXML file 'InicioSistema.fxml'.";
            assert btnUsuarios != null : "fx:id=\"btnUsuarios\" was not injected: check your FXML file 'InicioSistema.fxml'.";

        }
    }

