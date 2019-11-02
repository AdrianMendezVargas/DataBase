package com.example.myapplication2.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication2.entidad.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositorioDbImpl implements EstudianteRepositorio {

    private static  final String TABLE = "estudiante";
    private DbConexion dbConexion;

    public EstudianteRepositorioDbImpl(Context context) {
        this.dbConexion = new DbConexion(context);
    }

    @Override
    public void crear(Estudiante estudiante) {

        ContentValues cv = new ContentValues();

        cv.put("matricula", estudiante.getMatricula());
        cv.put("nombre", estudiante.getNombre());
        cv.put("carreraId", estudiante.getCarreraId());

       SQLiteDatabase db = dbConexion.getWritableDatabase();

      long id = db.insert(TABLE, null, cv);

      if (id <= 0){
          Log.i("EstudianteRepositorio", "Ocurio un error al crear Estudiante");
      } else{
          Log.i("EstudianteRepositorio", "El estudiante se ha creado, id = " +id);
      }

    }

    @Override
    public void actualizar(Estudiante estudiante) {

    }

    @Override
    public void borrar(Estudiante estudiante) {

    }

    @Override
    public Estudiante buscar(int id) {
        return null;
    }

    @Override
    public List<Estudiante> buscar() {
        List<Estudiante> estudiantes = new ArrayList();

        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.query(TABLE, new String[]{"id", "matricula", "nombre", "carreraId"}, null, null, null, null,null);

        while(c.moveToNext()){

           int id = c.getInt(c.getColumnIndex("id"));
           String matricula = c.getString(c.getColumnIndex("matricula"));
           String nombre = c.getString(c.getColumnIndex("nombre"));
           String carreraId = c.getString(c.getColumnIndex("carreraId"));

           Estudiante est = new Estudiante();
           est.setId(id);
           est.setMatricula(matricula);
           est.setNombre(nombre);
           est.setCarreraId(carreraId);

           estudiantes.add(est);

        }

        c.close();

        return estudiantes;

    }

}
