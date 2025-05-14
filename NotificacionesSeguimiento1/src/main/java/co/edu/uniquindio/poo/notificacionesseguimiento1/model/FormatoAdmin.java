package co.edu.uniquindio.poo.notificacionesseguimiento1.model;


public class FormatoAdmin extends TemplateMensaje {

    @Override
    public String encabezado(){
            return "[ADMINISTRADOR] \n";
        }

        @Override
        public String Cuerpo(String mensaje){
            return mensaje + "\n";
        }

        @Override
        public String Final(){
            return "-- Sistema --";
        }
    }


