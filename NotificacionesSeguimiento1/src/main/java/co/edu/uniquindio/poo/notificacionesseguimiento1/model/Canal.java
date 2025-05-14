package co.edu.uniquindio.poo.notificacionesseguimiento1.model;



public class Canal {
    private String codigo;
    public NotificacionStrategy notificacion;

    public Canal(String codigo, NotificacionStrategy notificacion){

        this.codigo = codigo;
        this.notificacion = notificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public NotificacionStrategy getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(NotificacionStrategy notificacion) {
        this.notificacion = notificacion;
    }

    public String EnviarMensaje(String mensaje) {
        String messaje = notificacion.enviar(mensaje);
        return messaje;
    }

    public void setNotificacion(String notificacion){
        switch (notificacion) {
            case "SMS":
                this.notificacion = new NotificacionSMS();
                break;
            case "Email":
                this.notificacion = new NotificacionEmail();
                break;
            case "Push":
                this.notificacion = new NotificacionPush();
                break;
            default:
                System.out.println("No existe este tipo de notificación");
                this.notificacion = null;
                break;
        }
    }


    public String getNombreCanal() {
        if (notificacion instanceof NotificacionSMS) {
            return "SMS";
        } else if (notificacion instanceof NotificacionPush) {
            return "Push";
        } else if (notificacion instanceof NotificacionEmail) {
            return "Email";
        } else {
            return "Canal no encontrado";
        }
    }



}

