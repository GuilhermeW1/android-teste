package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.teste.adapter.LanguageAdapter;
import com.example.teste.adapter.PersonAdapter;
import com.example.teste.controllers.LanguageController;
import com.example.teste.controllers.PersonController;
import com.example.teste.models.Language;
import com.example.teste.models.Nota;
import com.example.teste.models.Person;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {



    Button btnNova;
    ListView ltvLista;
    ArrayList<Person> listagem;
    PersonAdapter adapter;
    Context context;
    PersonController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activitty);
        context = PersonActivity.this;

        btnNova = findViewById(R.id.personActivity_btnAddPerson);
        ltvLista = findViewById(R.id.personActivity_itemPerson_List);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddPersonActivity.class);
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