package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

import java.util.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class Central {

    private static Central instance;

    private final List<Filtro> filtros;


    private String nombre;
    private Collection<Mensaje> mensajes;
    private Collection<Usuario> usuarios;
    private Collection<Canal> canales;

    public Central(String nombre) {

        this.filtros = new ArrayList<>();
        filtros.add(new UsuarioBloqueado());
        filtros.add(new MensajeVacio());


        this.nombre = nombre;
        this.mensajes = new LinkedList<>();
        this.usuarios = new LinkedList<>();
        this.canales = new LinkedList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Collection<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Collection<Canal> getCanales() {
        return canales;
    }

    public void setCanales(Collection<Canal> canales) {
        this.canales = canales;
    }



    public boolean verificarUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (email.equals(usuario.getEmail())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que permite agregar Usuarios a la lista verificando que no esten duplicados.
     *
     * @param usuario
     * @return true si el Usuario fue agregado exitosamente, false si el Uusuario ya existe.
     */

    public boolean agregarUsuario(Usuario usuario) {
        boolean centinela = false;
        boolean esUnico = this.verificarUsuario(usuario.getEmail());
        if (esUnico) {
            usuarios.add(usuario);
            centinela = true;
            System.out.println("Usuario agregado exitosamente");
        } else {
            System.out.println("El Usuario ya existe");
        }
        return centinela;
    }


    /**
     * Metodo que permite eliminar un Usuario de la lista con su email.
     *
     * @param email
     * @return true si el Usuario fue eliminado exitosamente, false si no se encontró en la lista.
     */

    public boolean eliminarUsuario(String email) {
        boolean centinela = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                usuarios.remove(usuario);
                centinela = true;
                System.out.println("Usuario eliminado exitosamente");
                break;
            }
        }
        return centinela;
    }

    /**
     * Metodo que permite actualizar un Usuario de la lista usuarios con su email.
     *
     * @param email
     * @param actualizado
     * @return true si el Usuario fue actualizado exitosamente, false si no se encontró en la lista.
     */

    public boolean actualizarUsuario(String email, Usuario actualizado) {
        boolean centinela = false;

        for (Usuario usuario : this.usuarios) {
            if (usuario.getEmail().equals(email)) {
                usuario.setEmail(actualizado.getEmail());
                usuario.setNombre(actualizado.getNombre());
                usuario.setBlocked(actualizado.isBlocked());
                usuario.setCanalSeleccionado(actualizado.getCanalSeleccionado());
                centinela = true;
                break;
            }
        }
        return centinela;
    }




    /**
     * Metodo que permite verificar que no hayan Mnesajes repetidos en la lista
     * @param codigo
     * @return
     */

    public boolean verificarMensaje(String codigo) {
        for (Mensaje mensaje : mensajes) {
            if (codigo.equals(mensaje.getCodigo())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que permite agregar Mensajes a la lista verificando que no esten duplicados.
     *
     * @param mensaje
     * @return true si el Mensaje fue agregado exitosamente, false si el Mensaje ya existe.
     */

    public boolean agregarMensaje(Mensaje mensaje) {
        boolean centinela = false;
        boolean esUnico = this.verificarMensaje(mensaje.getCodigo());
        if (esUnico) {
            mensajes.add(mensaje);
            centinela = true;
            System.out.println("Mensaje agregado exitosamente");
        } else {
            System.out.println("El mensaje ya existe");
        }
        return centinela;
    }


    /**
     * Metodo que permite eliminar un Mensaje de la lista con su codigo.
     *
     * @param codigo
     * @return true si el Mensaje fue eliminado exitosamente, false si no se encontró en la lista.
     */

    public boolean eliminarMensaje(String codigo) {
        boolean centinela = false;
        for (Mensaje mensaje : mensajes) {
            if (mensaje.getCodigo().equals(codigo)) {
                mensajes.remove(mensaje);
                centinela = true;
                System.out.println("El mensaje fue eliminado exitosamente");
                break;
            }
        }
        return centinela;
    }


    /**
     * Metodo que permite verificar que no hayan Canales repetidos en la lista
     * @param codigo
     * @return
     */

    public boolean verificarCanal(String codigo) {
        for (Canal canal : canales) {
            if (codigo.equals(canal.getCodigo())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Metodo que permite agregar Canales a la lista verificando que no esten duplicados.
     *
     * @param canal
     * @return true si el Canal fue agregado exitosamente, false si el Canal ya existe.
     */

    public boolean agregarCanal(Canal canal) {
        boolean centinela = false;
        boolean esUnico = this.verificarCanal(canal.getCodigo());
        if (esUnico) {
            canales.add(canal);
            centinela = true;
            System.out.println("El Canal fue agregado exitosamente");
        } else {
            System.out.println("El Canal ya existe");
        }
        return centinela;
    }


    public String procesarMensaje(Mensaje mensaje, NotificacionStrategy estrategia) {
        for (Filtro filtro : filtros) {
            String resultadoFiltro = filtro.filtrar(mensaje);
            if (resultadoFiltro != null) {
                return resultadoFiltro;
            }
        }

        TemplateMensaje formato;
        if (mensaje.getUsuario().getTipo() == Tipo.Admin) {
            formato = new FormatoAdmin();
        } else if (mensaje.getUsuario().getTipo() == Tipo.Cliente) {
            formato = new FormatoCliente();
        } else {
            formato = new FormatoInvitado();
        }


        String mensajeFormateado = formato.FormatoMensaje(mensaje.getMensaje()) +", " + mensaje.getUsuario().getNombre();

        String resultadoEnvio = estrategia.enviar(mensajeFormateado);

        agregarMensaje(mensaje);

        System.out.println(resultadoEnvio);

        return resultadoEnvio;

    }

    public static Central getInstance() {
        if (instance == null) {
            instance = new Central("Central Principal");
        }
        return instance;
    }




}
