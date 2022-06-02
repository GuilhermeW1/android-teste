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
import java.util.Date;

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
                objeto.setDtNascimento(resultado.getString(resultado.getColumnIndexOrThrow("dataNascimento")));
                objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));

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
            values.put("dataNascimento", object.getDtNascimento());
            values.put("cpf", object.getCpf());

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
            valores.put("dataNascimento", objeto.getDtNascimento());
            valores.put("cpf", objeto.getCpf());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tables.TB_PERSON, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Tools.toastMessage("a"+ex.getMessage(), context);
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

                    String dataFromDb = resultado.getString(resultado.getColumnIndexOrThrow("dataNascimento"));
                    String data = Tools.converterData(dataFromDb, "yyyy-MM-dd", "dd/MM/yyyy");
                    objeto.setDtNascimento(data);

                    objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));

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

    public boolean excluir(int id){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);

            conexao.delete(Tables.TB_PERSON, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO EXCLUIR CONTROLLER", ex.getMessage());
            return false;
        }
    }

}
