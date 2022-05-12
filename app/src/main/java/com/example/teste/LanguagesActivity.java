package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.teste.adapter.LanguageAdapter;
import com.example.teste.controllers.LanguageController;
import com.example.teste.models.Language;
import com.example.teste.models.Nota;

import java.util.ArrayList;

public class LanguagesActivity extends AppCompatActivity {


    EditText txtNome;
    EditText txtDescricao;
    Language objeto;
    LanguageController controller;
    Context context;
    int id_linguagem;
    Spinner spinner;
    CheckBox favorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
        context = LanguagesActivity.this;
        favorit = findViewById(R.id.checkboxFavorit);
        spinner = findViewById(R.id.spininer);

        spinner.setAdapter(notaAdapter());

        //Verificar se veio algum EXTRA da tela anterior
        Bundle extras = getIntent().getExtras();
        try {
            if (extras != null) {
                id_linguagem = extras.getInt("id", 0);

                //buscar através desta chave
                controller = new LanguageController(context);
                objeto = controller.buscar(id_linguagem);

                if (objeto != null) {
                    txtNome.setText(objeto.getName());
                    txtDescricao.setText(objeto.getDescription());

                    if (objeto.getFavorito() == 1) {
                        favorit.setChecked(true);
                    } else {
                        favorit.setChecked(false);
                    }

                    for (int i = 0; i <= spinner.getAdapter().getCount(); i++) {


                        Nota nota = (Nota) spinner.getItemAtPosition(i);
                        if (objeto.getNota() == nota.getId()) {
                            System.out.println("entrei");
                            spinner.setSelection(i);
                            break;
                        }
                    }
                }
            } else {
                id_linguagem = 0;
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


    private ArrayAdapter<Nota> notaAdapter()
    {
        ArrayList<Nota> nota = new ArrayList<>();
        nota.add(new Nota(0, "Sem nota"));
        nota.add(new Nota(1, "1"));
        nota.add(new Nota(2, "2"));

        ArrayAdapter<Nota> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, nota);

        return adapter;

    }

    private void salvar(){
        try{

            String nome = txtNome.getText().toString().trim();
            String descricao = txtDescricao.getText().toString().trim();
            boolean favorito = favorit.isChecked();
            int favoritoBanco = 0;
            if(favorito){
                favoritoBanco = 1;
            }

            Nota nota = (Nota) spinner.getSelectedItem();

            if(!nome.equals("") && !descricao.equals("")) {

                if(nome.length() > 30){
                    //Globais.exibirMensagem(context,
                            //"O nome é muito grande, credo.");
                    return;
                }
                //TODO validacoes de nota etc

                objeto = new Language();
                objeto.setName(nome);
                objeto.setDescription(descricao);
                objeto.setFavorito(favoritoBanco);
                objeto.setNota(nota.getId());
                controller = new LanguageController(context);

                boolean retorno = false;
                if(id_linguagem == 0){
                    retorno = controller.incluir(objeto);
                }else{
                    objeto.setId(id_linguagem);
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