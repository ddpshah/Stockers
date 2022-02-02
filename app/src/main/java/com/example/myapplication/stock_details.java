package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class stock_details extends AppCompatActivity {
    DatabaseReference reference;
    List<Stocks> stocks;
    private static final String URL_DATA = "https://opensheet.elk.sh/1TxhkLIoaF91QxIk2FUF1vw2-MDdM-l6sJasvg9Qnqk4/Equity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);

        Intent intent=getIntent();
        String Issuer_name=intent.getStringExtra("Issuer");
        Log.d("Name_Issuer",Issuer_name);
        //Toast.makeText(this, Issuer_name, Toast.LENGTH_SHORT).show();
      ;
      stocks=new ArrayList<>();
        getDetails(Issuer_name);
       // Toast.makeText(this, stocks.get(0).getIssuerName().toString(),Toast.LENGTH_SHORT).show();

        

    }
/*"Security": "KLAC",
        "Issuer": "KLA Corporation ",
        "Prize": "390.27",
        "Open": "391.21",
        "Close": "388.03",
        "High": "393.58",
        "Low": "385.27",
        "Volume": "417873",
        "Mkt Cap": "58819658475",
        "High 52": "457.12",
        "Low 52": "273.24",
        "1 Day Change": "2.24",
        "Change %": "0.58",
        "No of shares": "150715000",
        "DESC"*/

    private void getDetails(String issuer) {
        TextView ticker=findViewById(R.id.tv_ticker_search);
        TextView issuerTV=findViewById(R.id.tv_registration_number);
        TextView price=findViewById(R.id.tv_engine_no);
        TextView open=findViewById(R.id.tv_owner_name);
        TextView close=findViewById(R.id.tv_vehicle_class);
        TextView low=findViewById(R.id.tv_fuel_type);
        TextView volume=findViewById(R.id.tv_model);
        TextView mkt=findViewById(R.id.tv_rc_status);

        //Toast.makeText(this, "getDetails", Toast.LENGTH_SHORT).show();
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    stocks.clear();
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject stockObject=response.getJSONObject(i);
                            String name=stockObject.getString("Issuer".toString());
                            if(name.equals(issuer))
                            {   Stocks stock=new Stocks();
                                stock.setPrice(stockObject.getString("Prize".toString()));
                                stock.setSecurityCode(stockObject.getString("Security".toString()));
                                stock.setIssuerName(stockObject.getString("Issuer".toString()));
                               issuerTV.setText(stock.getIssuerName());
                               ticker.setText(stockObject.getString("Security".toString()));
                               price.setText(stockObject.getString("Prize".toString()));
                               open.setText(stockObject.getString("Open".toString()));
                               close.setText(stockObject.getString("Close".toString()));
                                low.setText(stockObject.getString("Low".toString()));
                                volume.setText(stockObject.getString("Volume".toString()));
                                mkt.setText(stockObject.getString("Mkt Cap".toString()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonArrayRequest);

    }

    private void setIssuerName(String string, Stocks stock) {
        stock.setIssuerName(string);
    }
}