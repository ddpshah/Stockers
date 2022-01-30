package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;


import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search_stock extends AppCompatActivity implements adapter_search.MyViewHolder.OnClickOrder{
    DatabaseReference ref;
    ArrayList<recycler_search> list;
    private RecyclerView recyclerView;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stock);

        ref = FirebaseDatabase.getInstance("https://hackathon-f2007-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Tickers");
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        searchView = findViewById(R.id.searchview);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                           // Log.d("VALUES",);

                            //list.add(new recycler_search(ds.getKey(),))
                            list.add(ds.getValue(recycler_search.class));

                        }
                        adapter_search adapter = new adapter_search(list,Search_stock.this);
                        Log.e("LIST EARLY",Integer.toString(list.size())) ;
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Search_stock.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
            });

        }
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=1)
                    search(s.toString());
                if(s.length()==0)
                    onStart();
            }
        });
    }


    private void search(String str) {
        ArrayList<recycler_search>templist = new ArrayList<>();
        Log.e("STOCKS",Integer.toString(list.size()));
        Log.e("VALUE",list.get(0).getIssuer());
        for (int i =0;i<list.size();i++) {
            if (list.get(i).getIssuer().toUpperCase().contains(str.toUpperCase())) {
                templist.add(list.get(i));
            }
        }
        adapter_search adapterSearch = new adapter_search(templist,Search_stock.this);
        recyclerView.setAdapter(adapterSearch);
    }
    @Override
    public void getIssuerName(String issuer) {
        Log.d("ISSER NAME",issuer);
        Intent intent=new Intent(getApplicationContext(),stock_details.class);
        intent.putExtra("Issuer",issuer);
        startActivity(intent);
    }
}
