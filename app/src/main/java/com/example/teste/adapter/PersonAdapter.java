package com.example.teste.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teste.LanguagesActivity;
import com.example.teste.PersonActivity;
import com.example.teste.R;
import com.example.teste.Tools;
import com.example.teste.models.Language;
import com.example.teste.models.Person;

import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter<Person> {

    private final Context context;
    private final ArrayList<Person> elementos;

    public PersonAdapter(Context context, ArrayList<Person> elementos){
        super(context, R.layout.item_list_person, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Person objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_list_person, parent, false);


            TextView name = rowView.findViewById(R.id.lblName_item_language);
            name.setText(objeto.getName());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, PersonActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO LANGUAGE ADAPTER GETVIEW", ex.getMessage());
            return null;
        }

    }
}
