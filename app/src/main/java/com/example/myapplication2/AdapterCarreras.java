package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.entidad.Carrera;

import java.util.List;

public class AdapterCarreras extends BaseAdapter {

    Context contexto;
    List<Carrera> listCarreras;

    public AdapterCarreras(Context contexto, List<Carrera> listCarrera) {
        this.contexto = contexto;
        this.listCarreras = listCarrera;
    }

    @Override
    public int getCount() {
        return listCarreras.size();
    }

    @Override
    public Object getItem(int position) {
        return listCarreras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from(contexto);

        view = inflater.inflate(R.layout.list_item_carrera, null);

        ImageView imagen = view.findViewById(R.id.iv_fotoCarrera);
        TextView nombreCarrera = view.findViewById(R.id.tv_nombreCarrera);
        TextView creditos= view.findViewById(R.id.tv_creditos);
        TextView materias = view.findViewById(R.id.tv_materias);

        nombreCarrera.setText(listCarreras.get(position).getNombre());
        imagen.setImageResource(R.drawable.carrera);

        return view;

    }
}
