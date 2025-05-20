// NotificacionesSeguimiento1/src/main/java/co/edu/uniquindio/poo/notificacionesseguimiento1/model/main.java
package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class main {

    public static void main(String[] args) {
        // Crear central
        Central central = new Central("Central de Notificaciones");

        // Crear canales
        Canal canalSMS = new Canal("C1", new NotificacionSMS());
        Canal canalEmail = new Canal("C2", new NotificacionEmail());
        Canal canalPush = new Canal("C3", new NotificacionPush());

        // Agregar canales a la central
        central.agregarCanal(canalSMS);
        central.agregarCanal(canalEmail);
        central.agregarCanal(canalPush);

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan", "juan@email.com", false, Tipo.Cliente, canalSMS);
        Usuario usuario2 = new Usuario("Ana", "ana@email.com", false, Tipo.Admin, canalEmail);
        Usuario usuario3 = new Usuario("Pedro", "pedro@email.com", false, Tipo.Invitado, canalPush);

        // Agregar usuarios a la central
        central.agregarUsuario(usuario1);
        central.agregarUsuario(usuario2);
        central.agregarUsuario(usuario3);

        // Crear mensajes
        Mensaje mensaje1 = new Mensaje("M1", usuario1, "Hola, este es un mensaje para el primero usuario.");
        Mensaje mensaje2 = new Mensaje("M2", usuario2, "Mensaje de la app para Ana.");
        Mensaje mensaje3 = new Mensaje("M3", usuario3, "Mensaje para usuario invitado.");

        // Ejecutar comandos de notificación
        NotificacionCommand cmd1 = new EnviarNotificacionCommand(mensaje1, canalSMS.getNotificacion(), central);
        NotificacionCommand cmd2 = new EnviarNotificacionCommand(mensaje2, canalEmail.getNotificacion(), central);
        NotificacionCommand cmd3 = new EnviarNotificacionCommand(mensaje3, canalSMS.getNotificacion(), central);

        System.out.println("--------------------------------------");
        cmd1.ejecutar();
        System.out.println("--------------------------------------");
        cmd2.ejecutar();
        System.out.println("--------------------------------------");
        cmd3.ejecutar();
    }
}
