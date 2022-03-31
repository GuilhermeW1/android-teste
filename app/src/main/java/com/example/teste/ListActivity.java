package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    Context context;
    ArrayList<String> lista;
    ListView listaView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaView = findViewById(R.id.ListViewTeste);
        context = ListActivity.this;

        lista = new ArrayList<String>();

        lista.add("abacate");

        for (int x=0; x<=10;x++){
            lista.add("contador "+String.valueOf(x));
        }
        lista.add("desgraca de array em java");




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, lista);
        listaView.setAdapter(adapter);
    }
}