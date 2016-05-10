package tarea.com.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        Button agregar=(Button) findViewById(R.id.button);
        Button cancelar=(Button) findViewById(R.id.button2);
        final EditText nom=(EditText)findViewById(R.id.txt_nombre);
        final EditText ape=(EditText)findViewById(R.id.txt_apellido);
        final EditText num=(EditText)findViewById(R.id.txt_numero);
        final EditText dui=(EditText)findViewById(R.id.txt_dui);
        final EditText car=(EditText)findViewById(R.id.txt_carrera);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent data = new Intent();
                    data.putExtra("Nombre",nom.getText().toString());
                    data.putExtra("Apellido",ape.getText().toString());
                    data.putExtra("Telefono",num.getText().toString());
                    data.putExtra("Dui", dui.getText().toString());
                    data.putExtra("Carrera", car.getText().toString());
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
