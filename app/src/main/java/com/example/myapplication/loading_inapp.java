package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class loading_inapp {

    private Activity activity;
    private AlertDialog dialog;

    loading_inapp(Activity myActivity){
        activity=myActivity;
    }

    void startloading(){

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_in_app,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();

    }

    void dismissDialog(){
        dialog.dismiss();
    }

}
