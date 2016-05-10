package tarea.com.contactos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private Control c=null;
    private int request_code = 1;//Nuevo
    private int request_code2 = 2;//Modificar
    private int indice=0;
    private Adaptador_contacto adap=null;
    private ListView lstContactos=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        c=new Control(getApplicationContext());
        adap = new Adaptador_contacto(this, c);
        lstContactos = (ListView)findViewById(R.id.listView);
        lstContactos.setAdapter(adap);

        lstContactos.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        indice=pos;
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        // Elimina y refresca
                                        c.Eliminar(indice);
                                        adap.notifyDataSetChanged();
                                        Toast.makeText(MainActivity.this,
                                                "Contacto Eliminado", Toast.LENGTH_SHORT).show();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(lstContactos.getContext());
                        builder.setMessage("¿Desea eliminar este contacto?").setPositiveButton("Si", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
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
                        n.putExtra("dui",c.getContactos().get(pos).getDUI());
                        n.putExtra("carrera",c.getContactos().get(pos).getCarrera());
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
                if (c.getContactos().get(i).getNumero().equals(data.getStringExtra("Telefono"))) {
                    existe = true;
                    indice = i;
                }
            }
            if (!existe) {
                c.Agregar(data.getStringExtra("Nombre"),data.getStringExtra("Apellido"),data.getStringExtra("Telefono"),
                        data.getStringExtra("Dui"),data.getStringExtra("Carrera"));
                Toast.makeText(MainActivity.this,
                        "Agregado "+data.getStringExtra("Nombre"), Toast.LENGTH_SHORT).show();
                adap.notifyDataSetChanged();
            }else{
                Intent n=new Intent(MainActivity.this, ModificarActivity.class);
                n.putExtra("nombre",c.getContactos().get(indice).getNombre());
                n.putExtra("apellido",c.getContactos().get(indice).getApellido());
                n.putExtra("telefono",c.getContactos().get(indice).getNumero());
                n.putExtra("dui",c.getContactos().get(indice).getDUI());
                n.putExtra("carrera",c.getContactos().get(indice).getCarrera());
                startActivityForResult(n, request_code2);
            }
        }
        if ((requestCode == request_code2) && (resultCode == RESULT_OK)){
            c.Modificar(indice,data.getStringExtra("Nombre"),data.getStringExtra("Apellido"),
                    data.getStringExtra("Telefono"),data.getStringExtra("Dui"),data.getStringExtra("Carrera"));
            Toast.makeText(MainActivity.this,
                    "Modificado "+data.getStringExtra("Nombre"), Toast.LENGTH_SHORT).show();
            adap.notifyDataSetChanged();
        }
    }

}
