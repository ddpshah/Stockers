package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class stock_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);

        Intent intent=getIntent();
        String Issuer_name=intent.getStringExtra("Issuer");
        Log.d("Name_Issuer",Issuer_name);
        Toast.makeText(this, Issuer_name, Toast.LENGTH_SHORT).show();
        addItemToSheet(Issuer_name);

    }

    private void addItemToSheet(String issuer_name) {

    }
}