package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.teste.adapter.LanguageAdapter;
import com.example.teste.adapter.PersonAdapter;
import com.example.teste.controllers.LanguageController;
import com.example.teste.controllers.PersonController;
import com.example.teste.models.Language;
import com.example.teste.models.Person;

import java.util.ArrayList;

public class PersonListActivity extends AppCompatActivity {

    Button btnNova;
    ListView ltvLista;
    ArrayList<Person> listagem;
    PersonAdapter adapter;
    Context context;
    PersonController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        context = PersonListActivity.this;

        btnNova = findViewById(R.id.btnAdd_new_person);
        System.out.println("cheguei aqui");
        ltvLista = findViewById(R.id.list_view_person);
        System.out.println("passei");

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("intent");
                Intent intent = new Intent(context, PersonActivity.class);
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

            controller = new PersonController(context);
            listagem = controller.lista();

            adapter = new PersonAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }
}