package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Button btnTabuada = findViewById(R.id.btnTabuada);
        Context context = MenuActivity.this;
        Button btnCalculadora = findViewById(R.id.btnCalculadora);
        Button btnViewWithTwoButtons = findViewById(R.id.btnView);
        Button btnToast = findViewById(R.id.btnToast);
        Button btnSnackbar = findViewById(R.id.btnSnackbar);
        Button btnLogout = findViewById(R.id.btnLogout);

        LinearLayout linear = findViewById(R.id.viewMenu);

        SharedPreferences sharedPreferences = getSharedPreferences("loginApp", Context.MODE_PRIVATE);;




        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TabuadaActivity.class);
                startActivity(intent);

            }
        });



        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CalculadoraActivity.class);
                startActivity(intent);

            }
        });

        btnViewWithTwoButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ButtonsActivitty.class);
                startActivity(intent);

            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "hellow world";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                toast.show();

            }
        });

        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(linear, "Juca bala", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", "");
                editor.apply();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}