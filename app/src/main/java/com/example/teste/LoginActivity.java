package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogar;
    EditText txtUser;
    EditText txtSenha;
    Context context;
    static String USER = "gui";
    static String PASSWORD = "123";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        btnLogar = findViewById(R.id.btnLogin);
        txtSenha = findViewById(R.id.txtPasswordLogin);
        txtUser = findViewById(R.id.txtUserLogin);


        //login auto
        sharedPreferences = getSharedPreferences("loginApp", Context.MODE_PRIVATE);
        String userPreference = sharedPreferences.getString("login", "");
        if(!userPreference.equals("")){
            Intent  intent = new Intent(context, MenuActivity.class);
            startActivity(intent);
            finish();
        }


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = txtUser.getText().toString();
                String senha = txtSenha.getText().toString();
                txtUser.requestFocus();
                //Toast.makeText(context, userPreference, Toast.LENGTH_LONG ).show();


                if(validateLogin(user, senha)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("login", user);//gettext, tostring
                    editor.apply();

                    Intent  intent = new Intent(context, MenuActivity.class);
                    startActivity(intent);
                    finish();
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