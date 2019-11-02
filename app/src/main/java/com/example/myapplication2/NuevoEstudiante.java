package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication2.entidad.Carrera;
import com.example.myapplication2.entidad.Estudiante;
import com.example.myapplication2.repositorio.DbConexion;
import com.example.myapplication2.repositorio.EstudianteRepositorio;
import com.example.myapplication2.repositorio.EstudianteRepositorioDbImpl;

import java.util.ArrayList;
import java.util.List;

public class NuevoEstudiante extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;
    EditText et_nom;
    EditText et_mat;
    Spinner sp_opcionesCarreras;
    ArrayList<String> carrerasNombre;
    ArrayList<Carrera> carreras;
    String estudianteCarreraId;

    DbConexion dbConexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_estudiante);

        dbConexion = new DbConexion(getApplicationContext());

        consultarCarreras();

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        List<Estudiante> estudianteList = estudianteRepositorio.buscar();

        for(Estudiante e: estudianteList){
            Log.i("Estudiante", e.getNombre());
        }

        Log.i("Estudiante", "Done!");

        et_nom = findViewById(R.id.et_nom);
        et_mat = findViewById(R.id.et_mat);
        sp_opcionesCarreras = findViewById(R.id.sp_opcionesCarreras);

        consultarCarreras();

        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carrerasNombre);

        sp_opcionesCarreras.setAdapter(spinnerAdapter);

        sp_opcionesCarreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              estudianteCarreraId = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void consultarCarreras() {
        SQLiteDatabase db = dbConexion.getReadableDatabase();

        carreras = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM carrera", null);

        while(c.moveToNext()){
            Carrera carrera = new Carrera();

            carrera.setId(c.getString(c.getColumnIndex("id")));
            carrera.setNombre(c.getString(c.getColumnIndex("nombre")));

            Log.i("id: ", carrera.getId());
            Log.i("nombre: ", carrera.getNombre());

            carreras.add(carrera);
        }
        c.close();

        obtenerNombreCarreras();

    }

    private void obtenerNombreCarreras() {
        carrerasNombre = new ArrayList<>();

        carrerasNombre.add("Seleccionar Carrera");

        for (int i = 0; i < carreras.size(); i++){
            carrerasNombre.add(carreras.get(i).getNombre());
        }

    }

    public void guardar(View v){

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());



        String nombre = et_nom.getText().toString();
        String matricula = et_mat.getText().toString();

        if(nombre.equals("") || matricula.equals("") || estudianteCarreraId.equals("Seleccionar Carrera")){

            Toast.makeText(getApplicationContext(),"Todos los campos deben estar completos",Toast.LENGTH_LONG).show();

        }else {

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(nombre);
            estudiante.setMatricula(matricula);
            estudiante.setCarreraId(estudianteCarreraId);

            estudianteRepositorio.crear(estudiante);

            Toast.makeText(getApplicationContext(),"Estudiante Creado",Toast.LENGTH_LONG).show();

            et_nom.setText("");
            et_mat.setText("");
        }

    }

    public void Cancelar(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void nuevaCarrera(View view){
        Intent i = new Intent(this, CarreraList.class);
        startActivity(i);
    }

}
