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

            controller = new LanguageController(context);
            listagem = controller.lista();

            adapter = new LanguageAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            //Globais.exibirMensagem(context, ex.getMessage());
            //Log.e("ERRO", ex.getMessage());
        }
    }

    /*
    Context context;
    ListView listaView;
    Button btnSalvar;
    EditText txtItem, txtDescription;
    ArrayList<Language> lista;
    LanguageAdapter adapter;
    Language object;
    LanguageController langController;
    int idLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        context = ListActivity.this;
        txtItem = findViewById(R.id.txtItem_lista);
        txtDescription = findViewById(R.id.txtItem_description);



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            object = new Language();
            idLanguage = extras.getInt("id", 0);
            langController = new LanguageController(context);

            object = langController.buscar(idLanguage);

        }


    }

    private void atualizarLista(){
        langController = new LanguageController(context);

        lista = new ArrayList<>();

        lista = langController.lista();
        if(lista != null){
            adapter = new LanguageAdapter(context, lista);
            listaView.setAdapter(adapter);
            System.out.println("entrei para setar a lista pela busca no banco de dados");
        }
    }


    private void salvar(){
        String item = txtItem.getText().toString().trim();

        String itemDescription = txtDescription.getText().toString().trim();

        if(!item.equals("") && !itemDescription.equals("")){
                    object = new Language();
                    object.setId(1);
                    object.setName(item);
                    object.setDescription(itemDescription);


                    lista.add(object);

                    adapter = new LanguageAdapter(context, lista);

                    listaView.setAdapter(adapter);

                    txtItem.setText("");
                    txtDescription.setText("");
                    txtItem.requestFocus();

            System.out.println("to adicionando um novo");
            object = new Language();
            object.setName(item);
            object.setDescription(itemDescription);

            langController = new LanguageController(context);

            if(langController.incluir(object)){
                Tools.toastMessage("Item adicionado", context);

            }else{
                Tools.toastMessage("Erro ao adicionar item", context);
            }


            txtItem.setText("");
            txtDescription.setText("");
            txtItem.requestFocus();

        }else{
            Tools.toastMessage("ta faltando coisa aa", context);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                //SALVAR
                salvar();

            case R.id.action_cancel:

                finish();


        }

        return super.onOptionsItemSelected(item);
    }


*/

}