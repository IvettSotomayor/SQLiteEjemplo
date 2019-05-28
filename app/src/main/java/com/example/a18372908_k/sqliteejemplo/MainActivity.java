package com.example.a18372908_k.sqliteejemplo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    private Button btnDelete;

    private CarsSQLiteHelper carsHelper;
    private SQLiteDatabase db;

    private ListView listView;
    private MyAdapter adapter;

    private List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        cars = new ArrayList<Car>();
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        btnCreate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                create();
                update();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //removeAll();
                deleteLast();
                update();
            }
        });

        //Abrimos la BD 'DBTest' en modo de escritura
        carsHelper = new CarsSQLiteHelper(this, "DBTest", null, 1);
        db = carsHelper.getWritableDatabase();

        adapter = new MyAdapter(this, cars, R.layout.itemdb);
        listView.setAdapter(adapter);

        update();
    }

    private List<Car> getAllCars(){
        //Seleccionamos todos los registros de la tabla Cars
        Cursor cursor = db.rawQuery("select * from Cars", null);
        List<Car> list = new ArrayList<Car>();
        if(cursor.moveToFirst()){
            //Iteramos sobre el cursor de resultados
            //Y vamos rellenando el array que posteriormente devolveremos
            while(cursor.isAfterLast()==false){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String color = cursor.getString(cursor.getColumnIndex("color"));

                list.add(new Car(id, nombre, color));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void create(){
        //Si hemos creado la BD correctamente
        if(db != null){
            ContentValues nuevoRegistro = new ContentValues();
            //El ID es autoincremental por eso mo se declara
            nuevoRegistro.put("nombre", "chevy");
            nuevoRegistro.put("color", "negro");

            db.insert("Cars", null, nuevoRegistro);
        }
    }

    private void removeAll(){
        db.delete("Cars", "id", null);
    }
    public void deleteLast(){
        Cursor cursor = db.rawQuery("select * from Cars", null);
        if(cursor.moveToLast()){
            String filaId = cursor.getString(cursor.getColumnIndex("id"));
            db.delete("Cars", "id" + "=?", new String[]{filaId});
        }
    }

    private void update(){
        //Borramos todos los elementos
        cars.clear();
        //Cargamos todos los elementos
        cars.addAll(getAllCars());
        //Refrescamos el adaptador
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy(){
        db.close();
        super.onDestroy();
    }
}
