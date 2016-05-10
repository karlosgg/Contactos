package tarea.com.contactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Admin on 08/05/2016.
 */
public class ContactoDB extends SQLiteOpenHelper {
    static final String nombre= "DB_Contactos";
    static final int version= 1;
    Context context;

    public ContactoDB(Context context, String nombre,  SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre, factory, version);
    }

    public ContactoDB(Context context){
        super(context, nombre, null, version);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS [Contacto] (\n" +
                "[Id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "[Nombres] VARCHAR(100)  NULL,\n" +
                "[Apellidos] VARCHAR(100)  NULL,\n" +
                "[DUI] VARCHAR(10)  NULL,\n" +
                "[Numero] VARCHAR(20)  NULL,\n" +
                "[Carrera] VARCHAR(100)  NULL\n" +
                ");\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void create(String Nombres, String Apellidos, String DUI, String Telefono, String Carrera){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            if(db!=null){
                ContentValues  values=new ContentValues();
                //values.put("Id", Id);
                values.put("Nombres", Nombres);
                values.put("Apellidos", Apellidos);
                values.put("DUI", DUI);
                values.put("Numero", Telefono);
                values.put("Carrera",  Carrera);
                db.insert("Contacto", null, values);
                Log.v("Exito","Ingresado correctamente");
            }
            db.close();
        }catch (SQLiteException e){
            Log.e("Error DB", e.toString());
        }
    }

    public ArrayList getAllContacts(){
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        Contacto contacto=new Contacto();
        SQLiteDatabase db=this.getWritableDatabase();
        if(db!=null){
            Cursor c=db.rawQuery("SELECT * FROM Contacto", null);
            if(c.moveToFirst()){
                do {
                    contacto.setId(c.getInt(0));
                    contacto.setNombre(c.getString(1));
                    contacto.setApellido(c.getString(2));
                    contacto.setDUI(c.getString(3));
                    contacto.setNumero(c.getString(4));
                    contacto.setCarrera(c.getString(5));
                    contactos.add(contacto);
                    contacto=new Contacto();
                }while (c.moveToNext());
            }
        }
        db.close();
        return contactos;
    }

    public void remove(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("Id", id);
                db.delete("Contacto","Id="+id,null);
            }
            db.close();
            Log.v("Eliminado contacto id", String.valueOf(id));
        } catch (SQLiteException e) {
            Log.e("Error DB", e.toString());
        }
    }

    public void update(int id,String nom, String ape, String dui, String tel, String car) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("Nombres", nom);
                values.put("Apellidos", ape);
                values.put("DUI", dui);
                values.put("Numero", tel);
                values.put("Carrera", car);
                db.update("Contacto", values, "Id="+id, null);
            }
            db.close();
            Log.v("Actualizado contacto", String.valueOf(id));
        } catch (SQLiteException e) {
            Log.e("Error DB", e.toString());
        }
    }
}
