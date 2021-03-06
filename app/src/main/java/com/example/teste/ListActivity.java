package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;


import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.teste.adapter.LanguageAdapter;
import com.example.teste.controllers.LanguageController;
import com.example.teste.models.Language;


import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Button btnNova;
    ListView ltvLista;
    ArrayList<Language> listagem;
    LanguageAdapter adapter;
    Context context;
    LanguageController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = ListActivity.this;

        btnNova = findViewById(R.id.btnAddLinguagem_linguagens);
        ltvLista = findViewById(R.id.ltvLista_linguagem);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LanguagesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {
            System.out.println("cheguei no atua");
            controller = new LanguageController(context);
            listagem = controller.lista();

            adapter = new LanguageAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }
}