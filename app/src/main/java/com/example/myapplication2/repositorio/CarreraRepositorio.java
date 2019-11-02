package com.example.myapplication2.repositorio;

import com.example.myapplication2.entidad.Carrera;

import java.util.List;

public interface CarreraRepositorio {

    void crear(Carrera carrera);
    void borrar(Carrera carrera);
    Carrera buscar(int c);
    List<Carrera> buscar();

}
