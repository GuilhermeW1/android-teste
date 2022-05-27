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
         String phoneNumber = phone;
         String formatedPhone =("(" + phoneNumber.substring(0, 2) + ") " + phoneNumber.substring(2, 7) + " - " + phoneNumber.substring(7, 11));

         return formatedPhone;
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
    public static Date convertStringToDate(String date){
         try {

             Date formatedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
             return formatedDate;
         }catch (Exception e){
             System.out.println(e.getMessage().toString());
             return null;
         }
    }

    public static String convertDateToString(Date date, String formatToParse){
         String dataToString = new SimpleDateFormat(formatToParse).format(date);
         return dataToString;
    }


}

