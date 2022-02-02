package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PortfolioFragment extends Fragment {
    private static final String URL_DATA = "https://opensheet.elk.sh/1TxhkLIoaF91QxIk2FUF1vw2-MDdM-l6sJasvg9Qnqk4/Equity";
    private static final String MARKET_URL = "https://opensheet.elk.sh/1n_1WWV2sXd9_hwWWzrsI2NF9B_AqCkV6wCVAYjTqzFs/1";
    private RecyclerView recyclerView, recyclerViewEx;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapterEx;
    public static Handler handler = new Handler();
    public static Runnable runnable;
    private List<Stocks> stocks;
    ImageView search;
    private List<Exchange> exchangeList;

    public PortfolioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.RV_stocks_owned);
        stocks = new ArrayList<>();
        exchangeList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), stocks);


        recyclerViewEx = view.findViewById(R.id.RV_wallet);

        recyclerViewEx.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterEx = new ExchangeAdapter(getContext(), exchangeList);
        recyclerView.setAdapter(adapter);
        recyclerViewEx.setAdapter(adapterEx);
        loadExchange();
        loadRecyclerView();


        return view;

    }

    private void refresh(int milli) {
        runnable = new Runnable() {
            @Override
            public void run() {
                loadRecyclerView();
            }
        };
        handler.postDelayed(runnable, milli);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        handler.postDelayed(runnable, milli);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        handler.removeCallbacks(runnable);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        handler.removeCallbacks(runnable);
                        break;
                }
            }
        });
    }

    private void refresh(int milli, int a) {
        runnable = new Runnable() {
            @Override
            public void run() {
                loadExchange();
            }
        };
        handler.postDelayed(runnable, milli);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        handler.postDelayed(runnable, milli);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        handler.removeCallbacks(runnable);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        handler.removeCallbacks(runnable);
                        break;
                }
            }
        });
    }

    private void loadRecyclerView() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    stocks.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject stockObject = response.getJSONObject(i);
                            Stocks stock = new Stocks();
                            stock.setPrice(stockObject.getString("Prize".toString()));
                            stock.setSecurityCode(stockObject.getString("Security".toString()));
                            stock.setIssuerName(stockObject.getString("Issuer".toString()));
                            //Log.e("Check", stockObject.getString("Prize".toString()));
                            stocks.add(stock);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error listener", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
            refresh(2000);
        } catch (Exception e) {
            //  Log.e("Exception",e.toString());
        }
    }


    private void loadExchange() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, MARKET_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject exchangeObject = response.getJSONObject(i);
                            Exchange exchange = new Exchange();
                            exchange.setName(exchangeObject.getString("name".toString()));
                            exchange.setPrince(exchangeObject.getString("price".toString()));
                            Log.e("Check", exchangeObject.getString("price".toString()));
                            exchangeList.add(exchange);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapterEx.notifyDataSetChanged();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error listener", Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(jsonArrayRequest);
            refresh(100000);
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        recyclerViewEx.setAdapter(adapterEx);
        adapterEx.notifyDataSetChanged();
    }
}