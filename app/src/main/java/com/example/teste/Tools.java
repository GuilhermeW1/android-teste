package com.example.teste;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.teste.models.Nota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Tools {

     public static void showAlert(String title, String message, Context context){
        try{
            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            alert.setTitle(title).setMessage(message).setPositiveButton("OK", null).show();
        }catch (Exception ex){
            Log.e("MostrarAlerta", ex.getMessage());
        }


    }
    public static void toastMessage(String message, Context context){

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }


    public static String parsePhoneNumber(String phone){
         try {
             String phoneNumber = phone;
             String formatedPhone = ("(" + phoneNumber.substring(0, 2) + ") " + phoneNumber.substring(2, 7) + " - " + phoneNumber.substring(7, 11));

             return formatedPhone;
         }catch (Exception e){
             return "";
         }
  }

    public static String converterData(String data, String formatoInicial, String formatoDesejado) {
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
        } finally {
            return wDataConvertida;
        }
    }
    public static String parseCpf(String cpf){
         try {
             String formatedCpf = "";
             formatedCpf = (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));

             return formatedCpf;
         }catch (Exception e){
        return "";
    }
    }

    public static String soNumero(String texto){
         try{
             String retorno = texto.replaceAll("\\D+", "");
             return  retorno;
         }catch (Exception e){
             return "";
         }
    }


}

