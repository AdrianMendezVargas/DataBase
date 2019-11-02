package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.entidad.Estudiante;

import java.util.List;

public class AdapterEstudiantes extends BaseAdapter {

    Context contexto;
    List<Estudiante> listEstudiantes;

    public AdapterEstudiantes(Context contexto, List<Estudiante> listEstudiantes) {
        this.contexto = contexto;
        this.listEstudiantes = listEstudiantes;
    }

    @Override
    public int getCount() {
        return listEstudiantes.size();
    }

    @Override
    public Object getItem(int position) {
        return listEstudiantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEstudiantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        LayoutInflater inflate = LayoutInflater.from(contexto);
        view = inflate.inflate(R.layout.list_item_estudiante, null);

        ImageView imagen = view.findViewById(R.id.iv_imagen);
        TextView nombre = view.findViewById(R.id.tv_nombre_item);
        TextView matricula = view.findViewById(R.id.tv_matricula_item);
        TextView carrera = view.findViewById(R.id.tv_carrera);

        nombre.setText(listEstudiantes.get(position).getNombre());
        matricula.setText(listEstudiantes.get(position).getMatricula());
        carrera.setText(listEstudiantes.get(position).getCarreraId());
        imagen.setImageResource(R.drawable.user);


        return view;

    }

}
