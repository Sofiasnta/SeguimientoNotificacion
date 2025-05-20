package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

import java.util.ArrayList;
import java.util.List;

public class NotificacionSMS implements NotificacionStrategy {

    @Override
    public String enviar(String mensaje) {
        return "🔔Mensaje enviado por via SMS: \n" + mensaje;
    }

}
