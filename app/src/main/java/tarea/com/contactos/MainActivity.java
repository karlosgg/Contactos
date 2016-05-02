package tarea.com.contactos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Control c=new Control();
    int request_code = 1;
    int request_code2 = 2;
    int indice=0;
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, c.getLosContacto());
        final ListView lstContactos = (ListView)findViewById(R.id.listView);

        lstContactos.setAdapter(adaptador);

        lstContactos.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        // Elimina y refresca
                        c.Eliminar(pos);
                        adaptador.clear();
                        adaptador.addAll(c.getLosContacto());
                        adaptador.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this,
                                "Contacto Eliminado", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                }
        );

        lstContactos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        // Modificar
                        Intent n=new Intent(MainActivity.this, ModificarActivity.class);
                        indice=pos;
                        n.putExtra("nombre",c.getContactos().get(pos).getNombre());
                        n.putExtra("apellido",c.getContactos().get(pos).getApellido());
                        n.putExtra("telefono",c.getContactos().get(pos).getNumero());
                        startActivityForResult(n, request_code2);
                    }
                }
        );
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent n=new Intent(MainActivity.this, NuevoActivity.class);
            startActivityForResult(n, request_code);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            boolean existe=false;
            for(int i=0; i<c.getContactos().size();i++){
                if (c.getContactos().get(i).getNumero().equals(data.getStringExtra("Telefono"))){
                    existe=true;
                    indice=i;
                }
            }
            if(!existe){
                c.Agregar(data.getStringExtra("Nombre"),data.getStringExtra("Apellido"),data.getStringExtra("Telefono"));
                Toast.makeText(MainActivity.this,
                        "Agregado "+data.getStringExtra("Nombre"), Toast.LENGTH_SHORT).show();
                adaptador.clear();
                adaptador.addAll(c.getLosContacto());
                adaptador.notifyDataSetChanged();
            }else{
                Intent n=new Intent(MainActivity.this, ModificarActivity.class);
                n.putExtra("nombre",c.getContactos().get(indice).getNombre());
                n.putExtra("apellido",c.getContactos().get(indice).getApellido());
                n.putExtra("telefono",c.getContactos().get(indice).getNumero());
                startActivityForResult(n, request_code2);
            }
        }
        if ((requestCode == request_code2) && (resultCode == RESULT_OK)){
            c.Modificar(indice,data.getStringExtra("Nombre"),data.getStringExtra("Apellido"),data.getStringExtra("Telefono"));
            Toast.makeText(MainActivity.this,
                    "Modificado "+data.getStringExtra("Nombre"), Toast.LENGTH_SHORT).show();
            adaptador.clear();
            adaptador.addAll(c.getLosContacto());
            adaptador.notifyDataSetChanged();
        }
    }

}
