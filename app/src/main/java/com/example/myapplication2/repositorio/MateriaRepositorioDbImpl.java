package com.example.myapplication2.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication2.entidad.Materia;

import java.util.ArrayList;
import java.util.List;

public class MateriaRepositorioDbImpl implements MateriaRepositorio {

    private static final String TABLE = "materia";
    private DbConexion dbConexion;

    public MateriaRepositorioDbImpl(Context context) {
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public void crear(Materia materia) {

        ContentValues cv = new ContentValues();

        cv.put("id", materia.getCreditos());
        cv.put("nombre", materia.getNombre());
        cv.put("creditos", materia.getCreditos());

        SQLiteDatabase db = dbConexion.getWritableDatabase();

        long id = db.insert(TABLE, null, cv);

        if (id <= 0){
            Log.i("MateriaRepositorio", "Error al crear Materia");
        }else {
            Log.i("MateriaRepositorio", "Se ha creado la Materia");
        }

    }

    @Override
    public void borrar(Materia materia) {

    }

    @Override
    public Materia buscar(int m) {
        return null;
    }

    @Override
    public List<Materia> buscar() {
        List<Materia> materias = new ArrayList();

        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.query(TABLE, new String[]{"id", "nombre", "creditos"}, null, null, null,null,null);

        while (c.moveToNext()){

            String id = c.getString(c.getColumnIndex("id"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            int creditos = c.getInt(c.getColumnIndex("creditos"));

            Materia materia = new Materia();

            materia.setId(id);
            materia.setNombre(nombre);
            materia.setCreditos(creditos);

            materias.add(materia);

        }
        c.close();

        return materias;
    }

}
