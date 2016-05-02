package tarea.com.contactos;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29/04/2016.
 */
public class Control {
    private Contacto contacto=null;
    private List<Contacto> contactos=new ArrayList<Contacto>();

    public Control(){
        contacto = new Contacto();
        contacto.setNombre("Jose");
        contacto.setApellido("Grijalva");
        contacto.setNumero("7702087");

        contactos.add(contacto);

        contacto = new Contacto();
        contacto.setNombre("Maria");
        contacto.setApellido("Ramos");
        contacto.setNumero("24334167");

        contactos.add(contacto);

        contacto = new Contacto();
        contacto.setNombre("Sandra");
        contacto.setApellido("Lopez");
        contacto.setNumero("77890007");

        contactos.add(contacto);
    }

    public List<Contacto> getContactos() { //Lista de contactos
        return contactos;
    }

    public List<String> getLosContacto(){ //Lista de nombres de contactos
        List nombres=new ArrayList();
        for (int i=0; i<contactos.size();i++){
            nombres.add("Nombre: "+contactos.get(i).getNombre()+"\nApellido: "+
                    getContactos().get(i).getApellido()+"\nTelefono: "+contactos.get(i).getNumero());
        }
        return nombres;
    }

    public void Agregar(String nom, String ape, String tel){ //Agregar Nuevo Contacto
        contacto=new Contacto(nom,ape,tel);
        contactos.add(contacto);
    }

    public void Eliminar(int indice){ //Eliminar contacto por indice
        contactos.remove(indice);
    }

    public void Modificar(int indice, String nom, String ape, String num){
        contactos.get(indice).setNombre(nom);
        contactos.get(indice).setApellido(ape);
        contactos.get(indice).setNumero(num);
    }
}
