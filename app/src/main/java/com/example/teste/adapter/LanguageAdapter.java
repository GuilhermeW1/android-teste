package com.example.teste.adapter;


    import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
    import android.widget.Toast;


    import com.example.teste.LanguagesActivity;
    import com.example.teste.ListActivity;
    import com.example.teste.R;
    import com.example.teste.Tools;
    import com.example.teste.models.Language;

    import java.util.ArrayList;

    public class LanguageAdapter extends ArrayAdapter<Language> {

        private final Context context;
        private final ArrayList<Language> elementos;

        public LanguageAdapter(Context context, ArrayList<Language> elementos){
            super(context, R.layout.item_list_language, elementos);
            this.context = context;
            this.elementos = elementos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            try{
                Language objeto = elementos.get(position);

                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //toda vez que passa por um item da lista, os elementos são associados
                View rowView = inflater.inflate(R.layout.item_list_language, parent, false);

                TextView description = rowView.findViewById(R.id.lblItem_description_language);
                TextView name = rowView.findViewById(R.id.lblName_item_language);
                //imgview favorito = row.findasdf......
                name.setText(objeto.getName());
                description.setText(objeto.getDescription());

                /*
                if(objeto.getFavorito() == 1){
                    favorito.setVisibility(View.VISIBLE);

                }else{
                    favorito.setVisibility(View.GONE);
                }
                */

                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tela = new Intent(context, LanguagesActivity.class);
                        tela.putExtra("id", objeto.getId());
                        context.startActivity(tela);
                    }
                });

                return rowView;

            }catch (Exception ex){
                Tools.toastMessage(ex.getMessage(), context);
                return null;
            }

        }
    }

