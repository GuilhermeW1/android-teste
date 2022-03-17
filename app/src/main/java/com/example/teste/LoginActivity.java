package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btnLogar;
    EditText txtUser;
    EditText txtSenha;
    Context context;
    static String USER = "gui";
    static String PASSWORD = "123";

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogar = findViewById(R.id.btnLogin);


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LoginActivity.this;



                txtSenha = findViewById(R.id.txtPasswordLogin);
                txtUser = findViewById(R.id.txtUserLogin);

                String user = txtUser.getText().toString();
                String senha = txtSenha.getText().toString();


                txtUser.requestFocus();


                if(validateLogin(user, senha)){
                    Intent  intent = new Intent(context, MenuActivity.class);
                    startActivity(intent);
                }else {
                    Tools.showAlert("Erro login", "Usuario ou senha invalidos", context);
                }



            }
        });

    }

    private static String validateSQLInjection(String senha){
        String senhaReplace;
        senhaReplace = senha.replaceAll("'", "");
        return senhaReplace;

    }
    private static boolean validateLogin(String user, String password){
        if(user.equals(USER) && password.equals(PASSWORD)){
            return true;
        }
        return false;
    }

}