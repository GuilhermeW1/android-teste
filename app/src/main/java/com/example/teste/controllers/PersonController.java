package com.example.teste.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.teste.Tools;
import com.example.teste.database.DadosOpenHelper;
import com.example.teste.database.Tables;

import com.example.teste.models.Language;
import com.example.teste.models.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PersonController {

    private SQLiteDatabase conexao;
    private Context context;

    public PersonController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }

    public Person buscar(int id){
        try{
            Person objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tables.TB_PERSON);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Person();

                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
                objeto.setPhone(resultado.getString(resultado.getColumnIndexOrThrow("phone")));

            }

            return objeto;

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO BUSCAR CONTROLLER", ex.getMessage());
            return null;
        }
    }

    public boolean insert(Person object){
        try {
            ContentValues values = new ContentValues();

            values.put("name", object.getName());
            values.put("phone", object.getPhone());

            String dataCerta = new SimpleDateFormat("yyyy-MM-dd").format(object.getDtNascimento());
            java.sql.Date dateSql = java.sql.Date.valueOf(dataCerta);

            values.put("dataNascimenot", String.valueOf(dateSql));

            conexao.insertOrThrow(Tables.TB_PERSON, null, values);

            return true;
        }catch (Exception e){
            Tools.toastMessage(e.getMessage(), context);
            Log.e("ERRO INSERT PERSON CONTROLLER", e.getMessage());
            return false;
        }
    }

    public boolean alterar(Person objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("name", objeto.getName());
            valores.put("phone", objeto.getPhone());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tables.TB_PERSON, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO ALTERAR PERSON CONTROLLER", ex.getMessage());
            return false;
        }
    }

    public ArrayList<Person> lista(){

        ArrayList<Person> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tables.TB_PERSON);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Person objeto;
                do{
                    objeto = new Person();

                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
                    String phoneFormat = Tools.parsePhoneNumber(resultado.getString(resultado.getColumnIndexOrThrow("phone")));
                    objeto.setPhone(phoneFormat);

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO LISTA CONTROLLER", ex.getMessage());
            return listagem;
        }
    }

}
