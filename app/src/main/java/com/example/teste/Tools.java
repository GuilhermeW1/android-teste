package com.example.teste;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.teste.models.Nota;

import java.util.ArrayList;

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





}

