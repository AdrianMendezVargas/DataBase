package com.example.myapplication2.repositorio;

import com.example.myapplication2.entidad.Estudiante;

import java.util.List;

public interface EstudianteRepositorio {

    void crear(Estudiante estudiante);
    void actualizar(Estudiante estudiante);
    void borrar(Estudiante estudiante);
    Estudiante buscar(int id);
    List<Estudiante> buscar();

}
