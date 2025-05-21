package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class GestorEventos implements EventoNotificacion {
    private final Central central;
    private static final String CODIGO_PREFIJO = "EVT";
    private int contadorEventos = 0;

    public GestorEventos(Central central) {
        this.central = central;
    }

    @Override
    public void notificarEvento(String mensaje) {
        contadorEventos++;
        String codigoEvento = CODIGO_PREFIJO + String.format("%03d", contadorEventos);

        for (Usuario usuario : central.getUsuarios()) {
            if (!usuario.isBlocked()) {
                Mensaje mensajeEvento = new Mensaje(codigoEvento, usuario, mensaje);
                NotificacionStrategy estrategia = determinarEstrategia(usuario.getCanalSeleccionado());

                NotificacionCommand comando = new EnviarNotificacionCommand(mensajeEvento, estrategia, central);
                comando.ejecutar();
            }
        }
    }

    private NotificacionStrategy determinarEstrategia(Canal canal) {
        if (canal == null) {
            return new NotificacionEmail();
        }

        switch (canal.getNombreCanal().toLowerCase()) {
            case "email":
                return new NotificacionEmail();
            case "sms":
                return new NotificacionSMS();
            case "push":
                return new NotificacionPush();
            default:
                return new NotificacionEmail();
        }
    }
}