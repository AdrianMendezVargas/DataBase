package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication2.entidad.Carrera;
import com.example.myapplication2.repositorio.CarreraRepositorio;
import com.example.myapplication2.repositorio.CarreraRepositorioDbImpl;

import java.util.List;

public class NuevaCarrera extends AppCompatActivity {

    EditText nombreCarrera;
    EditText idCarrera;
    CarreraRepositorio carreraRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_carrera);

        carreraRepositorio = new CarreraRepositorioDbImpl(this.getBaseContext());

        List<Carrera> carreras = carreraRepositorio.buscar();

        for (Carrera c : carreras) {
            Log.i("Carrera", c.getNombre());
        }

        Log.i("Carrera", "DONE!");

    }

    public void guargarCarrera(View view){

        carreraRepositorio = new CarreraRepositorioDbImpl(this.getBaseContext());

        nombreCarrera = (EditText) findViewById(R.id.et_nombreCarrera);
        idCarrera = (EditText) findViewById(R.id.et_idCarrera);

        String nombre = nombreCarrera.getText().toString();
        String id = idCarrera.getText().toString();

        if (nombre.equals("") || id.equals("")) {

            Toast.makeText(getApplicationContext(),"Todos los campos deben estar completos",Toast.LENGTH_LONG).show();

        }else {

            Carrera carrera = new Carrera();

            carrera.setId(id);
            carrera.setNombre(nombre);

            carreraRepositorio.crear(carrera);

            nombreCarrera.setText("");
            idCarrera.setText("");

            Toast.makeText(getApplicationContext(),"Se ha creado la carrera",Toast.LENGTH_LONG).show();

        }

    }
    public void cancelar(View view){
        Intent i = new Intent(this, CarreraList.class);
        startActivity(i);
    }


}
