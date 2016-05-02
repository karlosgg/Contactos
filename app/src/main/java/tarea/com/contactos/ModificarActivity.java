package tarea.com.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Button modificar=(Button) findViewById(R.id.btnmod);
        Button cancelar=(Button) findViewById(R.id.btncan);
        final EditText nom=(EditText)findViewById(R.id.txt_mod_nomb);
        final EditText ape=(EditText)findViewById(R.id.txt_mod_ape);
        final EditText num=(EditText)findViewById(R.id.txt_mod_num);
        nom.setText(getIntent().getStringExtra("nombre"));
        ape.setText(getIntent().getStringExtra("apellido"));
        num.setText(getIntent().getStringExtra("telefono"));

        Log.v("Nomre ", getIntent().getStringExtra("nombre"));
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("Nombre", nom.getText().toString());
                data.putExtra("Apellido", ape.getText().toString());
                data.putExtra("Telefono", num.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }
}
