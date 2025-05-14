package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class MensajeVacio extends Filtro{

    @Override
    public String filtrar(Mensaje mensaje) {
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isEmpty()) {
            return "Mensaje vacío. Notificación cancelada.";
        }
        return null;
    }

}
