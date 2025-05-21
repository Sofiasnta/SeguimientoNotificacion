package co.edu.uniquindio.poo.notificacionesseguimiento1.viewController;

import co.edu.uniquindio.poo.notificacionesseguimiento1.App;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Central;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.GestorEventos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EventosVC {

    private App app;
    @FXML
    private TextArea txtMensajeEvento;

    @FXML
    private Button btnEnviarEvento;

    @FXML
    private Button btnVolver;

    private Central central;
    private GestorEventos gestorEventos;

    public void initialize() {
        this.central = Central.getInstance();
        this.gestorEventos = new GestorEventos(central);
    }

    @FXML
    private void enviarEvento() {
        String mensajeEvento = txtMensajeEvento.getText();
        if (mensajeEvento != null && !mensajeEvento.trim().isEmpty()) {
            gestorEventos.notificarEvento(mensajeEvento);
            txtMensajeEvento.clear();
            central.getMensajes().forEach(mensaje -> System.out.println(mensaje.getCodigo()));
        }

    }



    @FXML
    private void volver() {
        try {
            app.openViewPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir la vista principal");
        }
    }


    public void setApp(App app) {
        this.app = app;

    }


}