package com.example.a18372908_k.sqliteejemplo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarsSQLiteHelper extends SQLiteOpenHelper{
    //Sentencia SQL para crear la tabla Cars
    String sqlCreate = "CREATE TABLE Cars (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, color TEXT)";

    public CarsSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.sqlCreate = sqlCreate;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Se elimina la version anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Cars");

        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}
