package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.teste.adapter.LanguageAdapter;
import com.example.teste.controllers.LanguageController;
import com.example.teste.models.Language;


import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Context context;
    ListView listaView;
    Button btnSalvar;
    EditText txtItem, txtDescription;
    ArrayList<Language> lista;
    LanguageAdapter adapter;
    Language object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaView = findViewById(R.id.ListViewTeste);
        context = ListActivity.this;
        txtItem = findViewById(R.id.txtItem_lista);
        btnSalvar = findViewById(R.id.btnSalvarItemLista);
        txtDescription = findViewById(R.id.txtItem_description);

        LanguageController langController = new LanguageController(context);

        lista = new ArrayList<>();

        lista = langController.lista();
        if(lista != null){
            adapter = new LanguageAdapter(context, lista);
            listaView.setAdapter(adapter);
            System.out.println("entrei para setar a lista pela busca no banco de dados");
        }




        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = txtItem.getText().toString().trim();

                String itemDescription = txtDescription.getText().toString().trim();

                if(!item.equals("") && !itemDescription.equals("")){
                    /*object = new Language();
                    object.setId(1);
                    object.setName(item);
                    object.setDescription(itemDescription);


                    lista.add(object);

                    adapter = new LanguageAdapter(context, lista);

                    listaView.setAdapter(adapter);

                    txtItem.setText("");
                    txtDescription.setText("");
                    txtItem.requestFocus();
                    */
                     object = new Language();
                     object.setName(item);
                     object.setDescription(itemDescription);

                    LanguageController langController = new LanguageController(context);
                    langController.incluir(object);
                    System.out.println("to adicionando um novo");

                }else{
                    Tools.toastMessage("ta faltando coisa aa", context);
                }
            }
        });

    }



}