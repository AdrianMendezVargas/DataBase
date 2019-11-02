package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication2.entidad.Estudiante;
import com.example.myapplication2.repositorio.EstudianteRepositorio;
import com.example.myapplication2.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(getBaseContext());

        List<Estudiante> estudiantes = estudianteRepositorio.buscar();

        ListView lv = findViewById(R.id.lv_estudiantes);

        AdapterEstudiantes adaptador = new AdapterEstudiantes(getApplicationContext(),estudiantes);

        lv.setAdapter(adaptador);

        //lv.setAdapter(new ArrayAdapter<>(getBaseContext(), R.layout.list_item_estudiante, estudiantes.toArray()));

    }

    public void agregarEstudiante(View view){
        Intent i = new Intent(this, NuevoEstudiante.class);
        startActivity(i);
    }
}
