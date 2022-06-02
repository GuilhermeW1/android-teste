package com.example.teste.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.teste.Tools;

import java.sql.SQLOutput;

public class DadosOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 6; //versão do banco de dados
    private static final String NM_BANCO = "bancao";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("to criando o banco a");
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tables.TB_LINGUAGENS);
            sql.append(" (");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" name VARCHAR(30) NOT NULL, ");
            sql.append(" description TEXT, ");
            sql.append(" favorito BIT, ");
            sql.append(" nota int ");
            sql.append(") ");

            db.execSQL(sql.toString());
            System.out.println("passei aqui");

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tables.TB_PERSON);
            sql.append(" (");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" name VARCHAR(30) NOT NULL, ");
            sql.append(" dataNascimento date, ");
            sql.append(" phone VARCHAR(30) NOT NULL, ");
            sql.append(" cpf VARCHAR(11) NOT NULL ");
            sql.append(") ");

            db.execSQL(sql.toString());



        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("erro ", ex.getMessage());
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql;
        System.out.println("to criando o banco");
        try{
            System.out.println("entrei aqui");
            System.out.println(newVersion);
            if(newVersion >= 5) {

                try{
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tables.TB_LINGUAGENS);
                    sql.append("  ADD COLUMN ");
                    sql.append(" favorito BIT ");

                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("Alter table add column favorito", ex.getMessage());

                }

                try{
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tables.TB_LINGUAGENS);
                    sql.append("  ADD COLUMN ");
                    sql.append(" nota int ");

                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("Alter table add column nota", ex.getMessage());
                }

                try{
                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tables.TB_PERSON);
                    sql.append(" (");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" name VARCHAR(30 NOT NULL ");
                    sql.append(") ");

                    db.execSQL(sql.toString());

                }catch (Exception e){
                    Log.e("alter table add person table", e.getMessage());
                }

                try{
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tables.TB_PERSON);
                    sql.append(" ADD COLUMN");
                    sql.append(" dataNascimento date ");

                    db.execSQL(sql.toString());

                }catch (Exception e){
                    Log.e("alter table add person table", e.getMessage());
                }

                try{
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tables.TB_PERSON);
                    sql.append(" ADD COLUMN");
                    sql.append(" phone VARCHAR(30) NOT NULL ");

                    db.execSQL(sql.toString());

                }catch (Exception e){
                    Log.e("alter table add person table", e.getMessage());
                }
                try{
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tables.TB_PERSON);
                    sql.append(" ADD COLUMN");
                    sql.append(" cpf VARCHAR(11) NOT NULL ");

                    db.execSQL(sql.toString());

                }catch (Exception e){
                    Log.e("alter table add person table", e.getMessage());
                }


            }

        }catch (Exception ex){
            ex.getMessage();
        }
    }

}
