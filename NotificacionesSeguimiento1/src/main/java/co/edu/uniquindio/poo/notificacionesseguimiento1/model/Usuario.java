package co.edu.uniquindio.poo.notificacionesseguimiento1.model;



public class Usuario {

    private String nombre;
    private String email;
    private boolean blocked;
    private Tipo tipo;
    private Canal canalSeleccionado;

    public Usuario(String nombre, String email, boolean blocked, Tipo tipo, Canal canalSeleccionado) {
        this.nombre = nombre;
        this.email = email;
        this.blocked = blocked;
        this.tipo = tipo;
        this.canalSeleccionado = canalSeleccionado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Canal getCanalSeleccionado() {
        return canalSeleccionado;
    }

    public void setCanalSeleccionado(Canal canalSeleccionado) {
        this.canalSeleccionado = canalSeleccionado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", blocked=" + blocked +
                ", tipo=" + tipo +
                ", canalSeleccionado=" + canalSeleccionado +
                '}';
    }
}
