package co.edu.uniquindio.poo.notificacionesseguimiento1.model;

public class Mensaje {
    private String codigo;
    private Usuario usuario;
    private String mensaje;
    private Filtro filtro;
    private TemplateMensaje templateMensaje;

    public Mensaje(String codigo, Usuario usuario, String mensaje) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public String getCodigo() {return codigo;}

    public Usuario getUser() {
        return usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Filtro getFiltro() {
        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public String enviarMensaje(String mensaje) {
        switch (usuario.getTipo()){
            case Cliente:
                templateMensaje = new FormatoCliente();
                break;
            case Invitado:
                templateMensaje = new FormatoInvitado();
                break;
            case Admin:
                templateMensaje = new FormatoAdmin();
                break;
        }
        return templateMensaje.FormatoMensaje(usuario.getCanalSeleccionado().EnviarMensaje(mensaje));
    }

}
