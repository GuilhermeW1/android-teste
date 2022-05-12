package com.example.teste.controllers;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.teste.Tools;
import com.example.teste.database.DadosOpenHelper;
import com.example.teste.database.Tables;
import com.example.teste.models.Language;

import java.util.ArrayList;

    public class LanguageController {

        private SQLiteDatabase conexao;
        private Context context;

        public LanguageController(Context context){
            DadosOpenHelper banco = new DadosOpenHelper(context);
            this.conexao = banco.getWritableDatabase();
            this.context = context;

        }

        public Language buscar(int id){
            try{
               Language objeto = null;

                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ");
                sql.append(Tables.TB_LINGUAGENS);
                sql.append(" WHERE id = '"+ id +"'");

                Cursor resultado = conexao.rawQuery(sql.toString(), null);
                if(resultado.moveToNext()){
                    objeto = new Language();

                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
                    objeto.setDescription(resultado.getString(resultado.getColumnIndexOrThrow("description")));
                    objeto.setFavorito(resultado.getInt(resultado.getColumnIndexOrThrow("favorito")));
                    objeto.setNota(resultado.getInt(resultado.getColumnIndexOrThrow("nota")));
                }

                return objeto;

            }catch (Exception ex){
                Tools.toastMessage(ex.getMessage(), context);
                Log.e("ERRO BUSCAR CONTROLLER", ex.getMessage());
                return null;
            }
        }

        public boolean incluir(Language objeto){
            try{

                ContentValues valores = new ContentValues();
                valores.put("name", objeto.getName());
                valores.put("description", objeto.getDescription());
                valores.put("favorito", objeto.getFavorito());
                valores.put("nota", objeto.getNota());

                ContentValues test = valores;

                conexao.insertOrThrow(Tables.TB_LINGUAGENS, null, valores);

                return true;

            }catch (Exception ex){
                Tools.toastMessage(ex.getMessage(), context);
                Log.e("ERRO INCLUIR CONTROLLER", ex.getMessage());
                return false;
            }
        }

        public boolean alterar(Language objeto){
            try{

                ContentValues valores = new ContentValues();
                valores.put("name", objeto.getName());
                valores.put("description", objeto.getDescription());
                valores.put("favorito", objeto.getFavorito());
                valores.put("nota", objeto.getNota());

                String[] parametros = new String[1];
                parametros[0] = String.valueOf(objeto.getId());

                conexao.update(Tables.TB_LINGUAGENS, valores, "id = ?" , parametros);

                return true;

            }catch (Exception ex){
                Tools.toastMessage(ex.getMessage(), context);
                Log.e("ERRO ALTERAR CONTROLLER", ex.getMessage());
                return false;
            }
        }

        public boolean excluir(int id){
            try{

                String[] parametros = new String[1];
                parametros[0] = String.valueOf(id);

                conexao.delete(Tables.TB_LINGUAGENS, "id = ?", parametros);

                return true;

            }catch (Exception ex){
                Tools.toastMessage(ex.getMessage(), context);
                Log.e("ERRO EXCLUIR CONTROLLER", ex.getMessage());
                return false;
            }
        }

        public ArrayList<Language> lista(){

            ArrayList<Language> listagem = new ArrayList<>();
            try{

                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ");
                sql.append(Tables.TB_LINGUAGENS);

                Cursor resultado = conexao.rawQuery(sql.toString(), null);
                if(resultado.moveToFirst()){

                    Language objeto;
                    do{
                        objeto = new Language();

                        objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                        objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
                        objeto.setDescription(resultado.getString(resultado.getColumnIndexOrThrow("description")));
                        objeto.setFavorito(resultado.getInt(resultado.getColumnIndexOrThrow("favorito")));
                        objeto.setNota(resultado.getInt(resultado.getColumnIndexOrThrow("nota")));

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

