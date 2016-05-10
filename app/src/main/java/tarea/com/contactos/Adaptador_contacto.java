package tarea.com.contactos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Admin on 08/05/2016.
 */
public class Adaptador_contacto extends ArrayAdapter {
    Activity contex;
    Control datos;


    public Adaptador_contacto(Activity contex, Control datos) {
        super(contex, R.layout.adaptador_contacto, datos.getContactos());
        this.datos = datos;
        this.contex = contex;
    }
    public View getView(int position, View conteView, ViewGroup parent) {

        LayoutInflater inflater = contex.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptador_contacto, null);

        TextView Nombre = (TextView) item.findViewById(R.id.Nombre_a);
        Nombre.setText(datos.getContactos().get(position).getNombre());

        TextView Apellidos = (TextView) item.findViewById(R.id.Apellidos_a);
        Apellidos.setText(datos.getContactos().get(position).getApellido());

        TextView DUI = (TextView) item.findViewById(R.id.Dui_a);
        DUI.setText(datos.getContactos().get(position).getDUI());

        TextView Telefono = (TextView) item.findViewById(R.id.Telefono_a);
        Telefono.setText(datos.getContactos().get(position).getNumero());

        TextView Carrera = (TextView) item.findViewById(R.id.Carrera_a);
        Carrera.setText(datos.getContactos().get(position).getCarrera());


        return item;


    }


}
