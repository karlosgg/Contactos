package tarea.com.contactos;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29/04/2016.
 */
public class Control {
    private Contacto contacto=null;
    private List<Contacto> contactos=new ArrayList<Contacto>();
    ContactoDB sqlite=null;

    public Control(){
        /*contacto = new Contacto();
        contacto.setNombre("Jose");
        contacto.setApellido("Grijalva");
        contacto.setNumero("7702087");
        contacto.setDUI("10928762-1");
        contacto.setCarrera("Ing. de Sistemas Informáticos");

        contactos.add(contacto);*/
    }
    public Control(Context context){
        sqlite = new ContactoDB(context);
        contactos=sqlite.getAllContacts();
    }
    public List<Contacto> getContactos() { //Lista de contactos
        return contactos;
    }

    public void Agregar(String nom, String ape, String tel,String dui, String car){ //Agregar Nuevo Contacto
        contacto=new Contacto(nom,ape,tel,dui,car);
        sqlite.create(nom,ape,dui,tel,car);
        contactos.add(contacto);
    }

    public void Eliminar(int indice){ //Eliminar contacto por indice
        int id= contactos.get(indice).getId();
        sqlite.remove(id);
        contactos.remove(indice);
    }

    public void Modificar(int indice, String nom, String ape, String num,String dui, String car){
        int id= contactos.get(indice).getId();
        sqlite.update(id,nom,ape,dui,num,car);
        contactos.get(indice).setNombre(nom);
        contactos.get(indice).setApellido(ape);
        contactos.get(indice).setNumero(num);
        contactos.get(indice).setDUI(dui);
        contactos.get(indice).setCarrera(car);
    }
}
