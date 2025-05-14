package co.edu.uniquindio.poo.notificacionesseguimiento1.model;


public abstract class TemplateMensaje {

    public String FormatoMensaje(String mensaje){
        StringBuilder formato = new StringBuilder();
        formato.append(encabezado());
        formato.append(Cuerpo(mensaje));
        formato.append(Final());
        return formato.toString();
    }

    public abstract String encabezado();
    public abstract String Cuerpo(String mensaje);
    public abstract String Final();
}

