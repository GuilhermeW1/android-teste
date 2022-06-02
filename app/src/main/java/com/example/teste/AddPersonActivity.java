package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.teste.controllers.LanguageController;
import com.example.teste.controllers.PersonController;
import com.example.teste.models.Language;
import com.example.teste.models.Nota;
import com.example.teste.models.Person;
import com.santalu.maskara.widget.MaskEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddPersonActivity extends AppCompatActivity {

    Person objeto;
    PersonController controller;
    Context context;
    EditText txtNome;
    MaskEditText phoneNumber;
    MaskEditText txtCpf;
    int idPerson;
    Button btnExcluir;;

    EditText txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        txtNome = findViewById(R.id.addPersonActivity_txt_PersonName);

        phoneNumber = findViewById(R.id.addPersonActivity_mask_phoneNumber);

        txtData = findViewById(R.id.addPersonActivity_mask_date);
        txtCpf = findViewById(R.id.addPersonActivity_mask_cpf);
        btnExcluir = findViewById(R.id.addPersonActivity_button_delete);
        context = AddPersonActivity.this;

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idPerson > 0){
                    controller = new PersonController(context);
                    boolean ret = controller.excluir(idPerson);

                    if(ret){
                        Tools.toastMessage("Successo", context);
                        finish();
                    }
                }
            }
        });

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


                    String phoneFormated  = Tools.parsePhoneNumber(objeto.getPhone());
                    phoneNumber.setText(phoneFormated);
                    String data = objeto.getDtNascimento();
                    String dataFormatada = Tools.converterData(data, "yyyy-MM-dd", "dd/MM/yyyy");
                    txtData.setText(dataFormatada);

                    String formatedCpf = Tools.parseCpf(objeto.getCpf());
                    txtCpf.setText(formatedCpf);


                }
            } else {
                idPerson = 0;
                btnExcluir.setVisibility(View.GONE);
            }
        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }


        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario = Calendar.getInstance();

                Date data;

                try{
                    if(txtData.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtData.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                    int ano = calendario.get(Calendar.YEAR);
                    int mes = calendario.get(Calendar.MONTH);
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(context, mDateSetListener, ano, mes, dia).show();

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                   //Globais.exibirMensagem(context, "Erro", "Erro ao abrir data");
                }
            }
        });
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String data = String.valueOf(String.format("%02d", dayOfMonth)) + "/"+ String.valueOf(String.format("%02d", monthOfYear + 1)) + "/" + String.valueOf(String.format("%02d", year));

            txtData.setText(data);
        }
    };


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
            String phone = phoneNumber.getText().toString();
            String data = txtData.getText().toString();
            String cpf = txtCpf.getText().toString();

            if(!nome.equals("")) {

                if(nome.length() > 30){
                    //Globais.exibirMensagem(context,
                    //"O nome é muito grande, credo.");
                    return;
                }
                //TODO validacoes de nota etc

                objeto = new Person();
                objeto.setName(nome);
                String dataT = Tools.converterData(data, "dd/MM/yyyy", "yyyy-MM-dd");
                objeto.setDtNascimento(dataT);
                String formatedPhone = Tools.soNumero(phone);
                objeto.setPhone(formatedPhone);
                String formatedCpf = Tools.soNumero(cpf);
                objeto.setCpf(formatedCpf);


//                //gambiarra para contonar o mascaramento mal feito
//                Person newPerson = new Person();
//                if (!phone.equals("")) {
//                    objeto.setPhone(phone);
//                } else {
//                    newPerson = controller.buscar(idPerson);
//                    objeto.setPhone(newPerson.getPhone());
//                }
//
//                if (!cpf.equals("")) {
//                    objeto.setCpf(cpf);
//                } else {
//                    newPerson = controller.buscar(idPerson);
//                    objeto.setCpf(newPerson.getCpf());                 //
//                }
//                /////////////////////////////////////////////////////////


                controller = new PersonController(context);

                boolean retorno = false;
                if(idPerson == 0){
                    retorno = controller.insert(objeto);
                }else{
                    objeto.setId(idPerson);
                    retorno = controller.alterar(objeto);
                }

                if(retorno) {
                    Tools.toastMessage("Sucesso", context);
                    finish();
                }

            }

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }
    }
