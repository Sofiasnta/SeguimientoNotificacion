package co.edu.uniquindio.poo.notificacionesseguimiento1.controller;

import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Central;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Mensaje;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Usuario;

import java.util.Collection;

public class MensajeriaController {

    public Central central;

    public MensajeriaController(Central central) {
        this.central = central;
    }

    public boolean agregarMensaje(Mensaje mensaje) {
        if (central == null) {
            System.err.println("Error: La central no ha sido inicializada.");
            return false;
        }
        return central.agregarMensaje(mensaje);
    }

    /**
     * Metodo que obtiene la lista de mensajes registrados en la central.
     * @return Una colección de objetos mensaje si la central está inicializado.
     */

    public Collection<Mensaje> obtenerListaMensajes() {
        if (central == null) {
            System.err.println("Error: No se puede obtener la lista de usuarios porque el sistema es null.");
            return null;
        }
        return central.getMensajes();
    }

    /**
     * Metodo que elimina un mensaje de la central según su codigo.
     * @param codigo
     * @return true si el mensaje fue eliminado con éxito, false en caso de error.
     */

    public boolean eliminarMensaje(String codigo) {
        if (central == null) {
            System.err.println("Error: No se puede eliminar mensaje ya que la central es null.");
            return false;
        }
        return central.eliminarMensaje(codigo);
    }


    public String generarMensaje(Mensaje mensaje, String texto) {
        return mensaje.enviarMensaje(texto);
    }


    public Collection<Usuario> obtenerListaUsuarios(){
        if (central == null) {

        }
        return central.getUsuarios();
    }
}
