package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication2.entidad.Carrera;
import com.example.myapplication2.repositorio.CarreraRepositorio;
import com.example.myapplication2.repositorio.CarreraRepositorioDbImpl;

import java.util.List;

public class CarreraList extends AppCompatActivity {

    CarreraRepositorio carreraRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera_list);

        carreraRepositorio = new CarreraRepositorioDbImpl(getBaseContext());

        List<Carrera> carreras = carreraRepositorio.buscar();

        ListView lv = findViewById(R.id.lv_carreras);

        AdapterCarreras adapter = new AdapterCarreras(getApplicationContext(), carreras);

        lv.setAdapter(adapter);

    }
    public void agrearCarrera(View view){
        Intent i = new Intent(this, NuevaCarrera.class);
        startActivity(i);
    }

}
