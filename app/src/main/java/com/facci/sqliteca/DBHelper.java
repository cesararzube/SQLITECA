package com.facci.sqliteca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cesar Arzube on 25/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "cne_ca";
    public static final String TABLA_NOMBRE = "votantes_ca";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Nombre";
    public static final String COL_3 = "Apellido";
    public static final String COL_4 = "RecintoElectoral";
    public static final String COL_5 = "AñoNacimiento";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("create tabla %s(ID INTEGER PRIMARY KEY AUTOINCREMENTO,%s TEXT,%s INTEGER)", TABLA_NOMBRE,COL_2,COL_3,COL_4,COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(String.format("DROP TABLA IF EXISTS %s",TABLA_NOMBRE));
        onCreate(db);
    }

    public boolean Insertar(String Nombre,String Apellido, String RecintoElectoral, int AñoNacimiento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues.put(COL_2,Nombre);
        ContentValues.put(COL_3,Apellido);
        ContentValues.put(COL_4,RecintoElectoral);
        ContentValues.put(COL_5,AñoNacimiento);
        long resultado = db.insert(TABLA_NOMBRE,null,contentValues);

        if (resultado == -1)
            return false;
            else
            return true;
    }

    public Cursor selectVerTodos(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select + fron %s",TABLA_NOMBRE).null);
        return res;
    }

    public Integer eliminarRegistro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return bd.delete(TABLA_NOMBRE,"id = ?",new String[]{id});
    }

    public boolean modificarRegistro(String id,String Nombre, String Apellido, String RecintoElectoral, int AñoNacimiento );
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues.put(COL_2,Nombre);
    ContentValues.put(COL_3,Apellido);
    ContentValues.put(COL_4,RecintoElectoral);
    ContentValues.put(COL_5,AñoNacimiento);
    db.update(TABLA_NOMBRE,ContentValues,"id = ?",new String[]{id});
    return true;
}
