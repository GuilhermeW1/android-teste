package com.example.teste.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.teste.Tools;

public class DadosOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2; //versão do banco de dados
    private static final String NM_BANCO = "bancao";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tables.TB_LINGUAGENS);
            sql.append(" (");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" name VARCHAR(30) NOT NULL, ");
            sql.append(" description TEXT, ");
            sql.append(" favorito BIT ");
            sql.append(")");
            db.execSQL(sql.toString());

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql;
        try{
            if(oldVersion ==2) {
                sql = new StringBuilder();
                sql.append(" ALTER TABLE ");
                sql.append(Tables.TB_LINGUAGENS);
                sql.append(" ( ADD COLUMN ");
                sql.append(" favorito BIT ");
                sql.append(")");
                db.execSQL(sql.toString());
            }

        }catch (Exception ex){

        }
    }

}
