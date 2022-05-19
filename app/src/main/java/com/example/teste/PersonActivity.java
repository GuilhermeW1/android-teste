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
            System.out.println("cheguei no atua");
            controller = new PersonController(context);
            listagem = controller.lista();

            adapter = new PersonAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }

    /*
    EditText txtNome;
    EditText txtDescricao;
    Person objeto;
    PersonController controller;
    Context context;
    int idPerson;
    Button addPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activitty);

        txtNome = findViewById(R.id.txtPerson_name);
        context = PersonActivity.this;
        addPerson = findViewById(R.id.btnAddPerson);

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNome.getText().toString();

                salvar(name);
            }
        });

        //Verificar se veio algum EXTRA da tela anterior
        Bundle extras = getIntent().getExtras();
        try {
            if (extras != null) {
                idPerson = extras.getInt("id", 0);

                //buscar através desta chave
                controller = new PersonController(context);
                objeto = controller.buscar(idPerson);

                if (objeto != null) {
                    txtNome.setText(objeto.getName());
                }
            } else {
                idPerson = 0;
            }
        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }


    private void salvar(String name){
        try{

            String nome = name;

            if(!nome.equals("")) {

                if(nome.length() > 30){
                    //Globais.exibirMensagem(context,
                    //"O nome é muito grande, credo.");
                    return;
                }

                objeto = new Person();
                objeto.setName(nome);
                controller = new PersonController(context);

                boolean retorno = false;
                if(idPerson == 0){
                    retorno = controller.insert(objeto);
                }else{
                    objeto.setId(idPerson);
                    retorno = controller.alterar(objeto);
                }

                if(retorno) {
                    //Globais.exibirMensagem(context, "Sucesso");
                    finish();
                }

            }

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }


     */

}