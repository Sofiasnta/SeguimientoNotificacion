package co.edu.uniquindio.poo.notificacionesseguimiento1.controller;

import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Canal;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Central;
import co.edu.uniquindio.poo.notificacionesseguimiento1.model.Usuario;

import java.util.Collection;

public class UsuarioController {

        public Central central;

        public UsuarioController(Central central) {
            this.central = central;
        }

        public boolean agregarUsuario(Usuario usuario) {
            if (central == null) {
                System.err.println("Error: La central no ha sido inicializada.");
                return false;
            }
            return central.agregarUsuario(usuario);
        }

        /**
         * Metodo que obtiene la lista de usuarios registrados en la central.
         * @return Una colección de objetos Usuario si la central está inicializado.
         */

        public Collection<Usuario> obtenerListaUsuarios() {
            if (central == null) {
                System.err.println("Error: No se puede obtener la lista de usuarios porque la central es null.");
                return null;
            }
            return central.getUsuarios();
        }

        /**
         * Metodo que elimina un usuario de la central según su email.
         * @param email
         * @return true si el usuario fue eliminado con éxito, false en caso de error.
         */

        public boolean eliminarUsuario(String email) {
            if (central == null) {
                System.err.println("Error: No se puede eliminar el usuario ya que la central es null.");
                return false;
            }
            return central.eliminarUsuario(email);
        }

        public boolean actualizarUsuario(String email, Usuario usuario) {
            if (central == null) {
                System.err.println("Error: No se puede actualizar el usuario porque la central es null.");
                return false;
            }
            return central.actualizarUsuario(email, usuario);
        }

        public Collection<Canal> obtenerListaCanales(){
            if (central == null) {

            }
            return central.getCanales();
        }
}
