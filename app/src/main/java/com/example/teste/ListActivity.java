package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.teste.adapter.LanguageAdapter;
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

        lista = new ArrayList<>();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }else{
                    Tools.toastMessage("ta faltando coisa aa", context);
                }
            }
        });

    }



}