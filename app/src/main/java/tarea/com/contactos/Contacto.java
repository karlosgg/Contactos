package tarea.com.contactos;

import java.io.Serializable;

/**
 * Created by Admin on 29/04/2016.
 */
public class Contacto implements Serializable {
    public Contacto() { //constructor sin parametros
    }
    public Contacto(String n, String a, String t, String d, String c){
        this.nombre=n;
        this.apellido=a;
        this.numero=t;
        this.DUI=d;
        this.carrera=c;
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

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nombre,apellido,numero,DUI,carrera;
    private int id;
}
