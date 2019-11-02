package com.example.myapplication2.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication2.entidad.Carrera;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositorioDbImpl implements CarreraRepositorio{

    private static final String TABLE = "carrera";
    private DbConexion dbConexion;

    public CarreraRepositorioDbImpl(Context context) {
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public void crear(Carrera carrera) {

        ContentValues cv = new ContentValues();

        cv.put("id",carrera.getId());
        cv.put("nombre",carrera.getNombre());

        SQLiteDatabase db = dbConexion.getWritableDatabase();

        long id = db.insert(TABLE, null, cv);

        if (id <= 0){
            Log.i("CarreraRepositorio","Error al crear carrera");
        }else {
            Log.i("CarreraRepositorio","Se ha creado la carrera id = " +id);
        }

    }

    @Override
    public void borrar(Carrera carrera) {

    }

    @Override
    public Carrera buscar(int c) {
        return null;
    }

    @Override
    public List<Carrera> buscar() {

        List<Carrera> carreras = new ArrayList();

        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.query(TABLE, new String[]{"id", "nombre"}, null, null, null, null, null);

        while (c.moveToNext()){

            String id = c.getString(c.getColumnIndex("id"));
            String nombre = c.getString(c.getColumnIndex("nombre"));

            Carrera carrera = new Carrera();

            carrera.setId(id);
            carrera.setNombre(nombre);

            carreras.add(carrera);

        }

        c.close();

        return carreras;
    }
}
