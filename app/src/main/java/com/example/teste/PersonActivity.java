package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.teste.controllers.LanguageController;
import com.example.teste.controllers.PersonController;
import com.example.teste.models.Language;
import com.example.teste.models.Nota;
import com.example.teste.models.Person;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtDescricao;
    Person objeto;
    PersonController controller;
    Context context;
    int idPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_activitty);

        txtNome = findViewById(R.id.txtPerson_name);
        context = PersonActivity.this;

        //Verificar se veio algum EXTRA da tela anterior
        Bundle extras = getIntent().getExtras();
        try {
            if (extras != null) {
                idPerson = extras.getInt("id", 0);

                //buscar através desta chave
                controller = new PersonController(context);
                objeto = controller.buscar(idPerson);

                if (objeto != null) {
                    txtNome.setText(objeto.getName());
                }
            } else {
                idPerson = 0;
            }
        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }

    //Funcao para inflar o menu na tela
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

    private void salvar(){
        try{

            String nome = txtNome.getText().toString().trim();

            if(!nome.equals("")) {

                if(nome.length() > 30){
                    //Globais.exibirMensagem(context,
                    //"O nome é muito grande, credo.");
                    return;
                }

                objeto = new Person();
                objeto.setName(nome);
                controller = new PersonController(context);

                boolean retorno = false;
                if(idPerson == 0){
                    retorno = controller.insert(objeto);
                }else{
                    objeto.setId(idPerson);
                    retorno = controller.alterar(objeto);
                }

                if(retorno) {
                    //Globais.exibirMensagem(context, "Sucesso");
                    finish();
                }

            }

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }

}