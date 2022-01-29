package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockRecycler extends AppCompatActivity {
    private static final String URL_DATA="https://sheetdb.io/api/v1/p4s3llq942luu";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Stocks> stocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_stock_recycler);}}
