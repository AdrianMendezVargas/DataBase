package com.example.myapplication2.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbConexion extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private  final static String NAME_DB = "escuela.db";

    public DbConexion(Context context) {

        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE \"estudiante\" (\"id\"\tINTEGER,\"matricula\"\tTEXT,\"nombre\"\tTEXT,\"carreraId\"\tTEXT)");
        db.execSQL("CREATE TABLE \"carrera\" (\"id\"\tTEXT,\"nombre\"\tTEXT)");
        db.execSQL("CREATE TABLE \"materia\" (\"id\"\tTEXT,\"nombre\"\tTEXT,\"creditos\"\tINTEGER)");
        db.execSQL("CREATE TABLE \"carrera_materia\" (\"carrera_id\"\tTEXT,\"materia_id\"\tTEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
