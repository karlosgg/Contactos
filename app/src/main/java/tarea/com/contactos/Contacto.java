package tarea.com.contactos;

/**
 * Created by Admin on 29/04/2016.
 */
public class Contacto {
    public Contacto() { //constructor sin parametros
    }
    public Contacto(String n, String a, String t){
        this.nombre=n;
        this.apellido=a;
        this.numero=t;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    private String nombre;
    private String apellido;
    private String numero;
}
