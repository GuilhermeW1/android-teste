package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Button btnTabuada = findViewById(R.id.btnTabuada);
        Context context = MenuActivity.this;

        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TabuadaActivity.class);
                startActivity(intent);

            }
        });

        Button btnCalculadora = findViewById(R.id.btnCalculadora);


        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CalculadoraActivity.class);
                startActivity(intent);

            }
        });

    }
}