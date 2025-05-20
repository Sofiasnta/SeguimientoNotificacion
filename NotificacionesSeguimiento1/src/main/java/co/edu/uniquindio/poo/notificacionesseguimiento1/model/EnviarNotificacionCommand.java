package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class EnviarNotificacionCommand implements NotificacionCommand{
    private final Mensaje mensaje;
    private final NotificacionStrategy estrategia;
    private final Central central;

    public EnviarNotificacionCommand(Mensaje mensaje, NotificacionStrategy estrategia, Central central) {
        this.mensaje = mensaje;
        this.estrategia = estrategia;
        this.central = central;
    }

    @Override
    public void ejecutar() {
        central.procesarMensaje(mensaje, estrategia);
    }

}
