package com.example.myapplication2.repositorio;

import com.example.myapplication2.entidad.Materia;

import java.util.List;

public interface MateriaRepositorio {

    void crear(Materia materia);
    void borrar(Materia materia);
    Materia buscar(int m);
    List<Materia> buscar();

}
