package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class stock_details extends AppCompatActivity {
    DatabaseReference reference;
    List<Stocks> stocks;
    ImageView back;
    private static final String URL_DATA = "https://opensheet.elk.sh/1TxhkLIoaF91QxIk2FUF1vw2-MDdM-l6sJasvg9Qnqk4/Equity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);

        Intent intent = getIntent();
        String Issuer_name = intent.getStringExtra("Issuer");
        Log.d("Name_Issuer", Issuer_name);
        //Toast.makeText(this, Issuer_name, Toast.LENGTH_SHORT).show();
        back=findViewById(R.id.btn_back_search);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Search_stock.class);
                startActivity(intent);
                finish();
            }
        });
        ;
        stocks = new ArrayList<>();
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
        final loading_inapp loadingdialog = new loading_inapp(stock_details.this);
        loadingdialog.startloading();
        TextView ticker = findViewById(R.id.tv_ticker_search);
        TextView issuerTV = findViewById(R.id.tv_issuer_name);
        TextView price = findViewById(R.id.tv_current_price);
        TextView open = findViewById(R.id.tv_open_today);
        TextView close = findViewById(R.id.tv_close_today);
        TextView high = findViewById(R.id.tv_high_today);
        TextView low = findViewById(R.id.tv_low_today);
        TextView volume = findViewById(R.id.tv_volume);
        TextView mkt = findViewById(R.id.tv_market_cap);
        TextView high52 = findViewById(R.id.tv_52_week_high);
        TextView low52 = findViewById(R.id.tv_52_week_low);
        TextView one_day_change = findViewById(R.id.tv_change_oneday);
        TextView change_percent = findViewById(R.id.tv_change_percent);
        TextView no_of_shares = findViewById(R.id.tv_no_shares);
       /* TextView Desc = findViewById(R.id.tv_desc);*/

        //Toast.makeText(this, "getDetails", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                stocks.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject stockObject = response.getJSONObject(i);
                        String name = stockObject.getString("Issuer".toString());
                        if (name.equals(issuer)) {
//                            Toast.makeText(stock_details.this, issuer, Toast.LENGTH_SHORT).show();
                            Stocks stock = new Stocks();
                            stock.setPrice(stockObject.getString("Prize".toString()));
                            stock.setSecurityCode(stockObject.getString("Security".toString()));
                            stock.setIssuerName(stockObject.getString("Issuer".toString()));
                            issuerTV.setText(stock.getIssuerName());
                            ticker.setText(stockObject.getString("Security".toString()));
                            price.setText(stockObject.getString("Prize".toString()));
                            open.setText(stockObject.getString("Open".toString()));
                            close.setText(stockObject.getString("Close".toString()));
                            high.setText(stockObject.getString("High".toString()));
                            low.setText(stockObject.getString("Low".toString()));
                            volume.setText(stockObject.getString("Volume".toString()));
                            mkt.setText(stockObject.getString("Mkt Cap".toString()));
                            high52.setText(stockObject.getString("High 52".toString()));
                            low52.setText(stockObject.getString("Low 52".toString()));
                            one_day_change.setText(stockObject.getString("1 Day Change".toString()));
                            change_percent.setText(stockObject.getString("Change %".toString()));
                            no_of_shares.setText(stockObject.getString("No of shares".toString()));
//                            Desc.setText(stockObject.getString("DESC".toString()));
                            loadingdialog.dismissDialog();


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        loadingdialog.dismissDialog();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingdialog.dismissDialog();

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void setIssuerName(String string, Stocks stock) {
        stock.setIssuerName(string);
    }
}