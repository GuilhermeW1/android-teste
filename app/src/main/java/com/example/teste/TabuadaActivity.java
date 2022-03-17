package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TabuadaActivity extends AppCompatActivity {

    TextView lblTabuada;
    Button btnCalcular;
    EditText txtTabuada;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btnCalculate_main);
        txtTabuada = findViewById(R.id.txtTabuada_main);
        lblTabuada = findViewById(R.id.lblTabuada_main);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context = TabuadaActivity.this;

                int numeroCalcular = 0;

                try {
                     numeroCalcular = Integer.parseInt(txtTabuada.getText().toString());

                } catch (Exception ex)
                {
                    Log.e("CALCULADORA", ex.getMessage());
                }

                if(validataeNumber(numeroCalcular))
                {
                    calculateAndPrintTabuada(numeroCalcular);
                }else
                {
                    Tools.showAlert("Numero Invalido", "Insira um numero valido", context);
                }
            }
        });


    }


    //make the times table until 10
    private void calculateAndPrintTabuada(int tabuada) {
        String impressao = "";

        for (int i = 0; i <= 10; i++) {
            int res = i * tabuada;

            impressao += tabuada + "X" + i + "=" + res + "\n";
        }

        lblTabuada.setText(impressao);

    }

    private boolean validataeNumber(int number){
        if(number == 0 || number < 0){
            return false;
        }

        return true;
    }



}