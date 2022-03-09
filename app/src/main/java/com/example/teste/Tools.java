package com.example.teste;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

public class Tools {

     public static void showAlert(String title, String message, Context context){
        try{
            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            alert.setTitle(title).setMessage(message).setPositiveButton("OK", null).show();
        }catch (Exception ex){
            Log.e("MostrarAlerta", ex.getMessage());
        }
    }
}
