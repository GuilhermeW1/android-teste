package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    Context context;
    ListView listaView;
    Button btnSalvar;
    EditText txtItem;
    ArrayList<String> lista;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaView = findViewById(R.id.ListViewTeste);
        context = ListActivity.this;
        txtItem = findViewById(R.id.txtItem_lista);
        btnSalvar = findViewById(R.id.btnSalvarItemLista);

        lista = new ArrayList<>();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = txtItem.getText().toString().trim();
                if(!item.equals("")){
                    lista.add(item);

                    adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
                    
                    listaView.setAdapter(adapter);

                    txtItem.setText("");
                    txtItem.requestFocus();
                }else{

                }
            }
        });

    }



}