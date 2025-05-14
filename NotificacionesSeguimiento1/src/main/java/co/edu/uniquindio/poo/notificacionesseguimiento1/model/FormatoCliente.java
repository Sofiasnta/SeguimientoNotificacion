package co.edu.uniquindio.poo.notificacionesseguimiento1.model;


public class FormatoCliente extends TemplateMensaje {

    @Override
    public String encabezado(){
        return "Bienvenido Usuario Cliente \n";
    }

    @Override
    public String Cuerpo(String mensaje){
        return mensaje + "\n";
    }

    @Override
    public String Final(){
        return "Gracias por usar nuestra aplicacion ";
    }
}
