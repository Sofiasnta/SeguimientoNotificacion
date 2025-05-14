package co.edu.uniquindio.poo.notificacionesseguimiento1.model;


public abstract class Filtro {

    protected Filtro next;

    public  Filtro getNext() {
        return next;
    }

    public void setNext(Filtro next) {
        this.next = next;
    }

    public String handle(Mensaje mensaje) {
        String resultado = filtrar(mensaje);
        if (resultado == null && next != null) {
            return next.handle(mensaje);
        }
        return resultado;
    }




 public abstract String filtrar(Mensaje mensaje);


}