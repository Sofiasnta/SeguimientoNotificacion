package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class NotificacionPush implements NotificacionStrategy {

    @Override
    public String enviar(String mensaje) {
        return "🔔Mensaje enviado por via Push: \n" + mensaje;
    }


}
