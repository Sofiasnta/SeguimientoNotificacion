package co.edu.uniquindio.poo.notificacionesseguimiento1;

import co.edu.uniquindio.poo.notificacionesseguimiento1.model.*;
import co.edu.uniquindio.poo.notificacionesseguimiento1.viewController.InicioSistemasVC;
import co.edu.uniquindio.poo.notificacionesseguimiento1.viewController.MensajeriaVC;
import co.edu.uniquindio.poo.notificacionesseguimiento1.viewController.UsuarioVC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    public static Central centralMensajeria = new Central("Sistema Notificaciones");
    private boolean datosInicializados=false;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.TRANSPARENT); // Hacer la ventana transparente
        this.primaryStage.setTitle("Bienvenido");
        openViewPrincipal();
    }


    private double xOffset = 0;
    private double yOffset = 0;


    public void openViewPrincipal() {
        if (!datosInicializados) {
            inicializarData();
            datosInicializados = true;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("InicioSistema.fxml"));
            Pane rootLayout = (Pane) loader.load();
            rootLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
           InicioSistemasVC primaryController = loader.getController();
            primaryController.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Usuario() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Usuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            UsuarioVC usuarioVC = loader.getController();
            usuarioVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Mensajeria() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Mensaje.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            MensajeriaVC mensajeriaVC = loader.getController();
            mensajeriaVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


    public void inicializarData() {
        Canal canalEmail = new Canal("EMAIL", new NotificacionEmail());
        Canal canalPush = new Canal("PUSH", new NotificacionPush());
        Canal canalSMS = new Canal("SMS", new NotificacionSMS());


        centralMensajeria.agregarCanal(canalEmail);
        centralMensajeria.agregarCanal(canalPush);
        centralMensajeria.agregarCanal(canalSMS);

        Usuario cliente1 = new Usuario("Sofi", "sofirendon@gmail.com", false, Tipo.Cliente , canalEmail );
        Usuario admin1 = new Usuario( "santi", "santiySofi@gmail.com", false, Tipo.Admin, canalPush);
        Usuario invitado1 = new Usuario("Macarena", "macarena123@gmail.com", false, Tipo.Invitado, canalSMS);

        centralMensajeria.agregarUsuario(cliente1);
        centralMensajeria.agregarUsuario(admin1);
        centralMensajeria.agregarUsuario(invitado1);



        Mensaje mensaje1= new Mensaje("C1", invitado1, "Actualizacion de datos realizada para: " +invitado1.getNombre());
        centralMensajeria.agregarMensaje(mensaje1);
    }
}