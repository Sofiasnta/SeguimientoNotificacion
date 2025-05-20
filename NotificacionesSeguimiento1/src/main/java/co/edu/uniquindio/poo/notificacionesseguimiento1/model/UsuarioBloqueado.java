package co.edu.uniquindio.poo.notificacionesseguimiento1.model;


public class UsuarioBloqueado extends Filtro {


    @Override
    public String filtrar(Mensaje mensaje) {
        if (mensaje.getUsuario().isBlocked()) {
            System.out.println("Usuario bloqueado. Notificación cancelada.");
            return "Usuario bloqueado. Notificación cancelada.";
        }
        return null;
    }
}
