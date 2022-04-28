package com.example.teste.controllers;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        public ArrayList<Language> preencher(){
            Language lang= new Language();
            StringBuilder sql = new StringBuilder();

            sql.append("select * from "+Tables.TB_LINGUAGENS);

            Cursor res = conexao.rawQuery(sql.toString(), null);

            lang.setName(res.getString(res.getColumnIndexOrThrow("name")));
            lang.setDescription(res.getString(res.getColumnIndexOrThrow("description")));

            ArrayList<Language> lista = new ArrayList<>();

            lista.add(lang);

            return lista;

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
                    //objeto.setFavorito(resultado.getInt.getcolumnu....."favorito")
                }

                return objeto;

            }catch (Exception ex){
                //Globais.exibirMensagem(context, ex.getMessage());
                return null;
            }
        }

        public boolean incluir(Language objeto){
            try{

                ContentValues valores = new ContentValues();
                valores.put("name", objeto.getName());
                valores.put("description", objeto.getDescription());

                conexao.insertOrThrow(Tables.TB_LINGUAGENS, null, valores);

                return true;

            }catch (Exception ex){
                //Globais.exibirMensagem(context, ex.getMessage());
                return false;
            }
        }

        public boolean alterar(Language objeto){
            try{

                ContentValues valores = new ContentValues();
                valores.put("name", objeto.getName());
                valores.put("description", objeto.getDescription());
                //valore.put("favorito", objeto.getFavorito())

                String[] parametros = new String[1];
                parametros[0] = String.valueOf(objeto.getId());

                conexao.update(Tables.TB_LINGUAGENS, valores, "id = ?" , parametros);

                return true;

            }catch (Exception ex){
                //Globais.exibirMensagem(context, ex.getMessage());
                return false;
            }
        }

        public boolean excluir(Language objeto){
            try{

                String[] parametros = new String[1];
                parametros[0] = String.valueOf(objeto.getId());

                conexao.delete(Tables.TB_LINGUAGENS, "id = ?", parametros);

                return true;

            }catch (Exception ex){
                //Globais.exibirMensagem(context, ex.getMessage());
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

                        listagem.add(objeto);

                    }while (resultado.moveToNext());

                }

                return listagem;

            }catch (Exception ex){
               // Globais.exibirMensagem(context, ex.getMessage());
                return listagem;
            }
        }

    }

