package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

LayoutInflater inflater;
List<Stocks> stocksList;

public Adapter(Context ctx, List<Stocks> stocksList){
    this.inflater=LayoutInflater.from(ctx);
    this.stocksList=stocksList;
}

    @NonNull
    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
       View view=inflater.inflate(R.layout.stock_layout,parent,false);
       return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder( Viewholder holder, int position) {

        holder.Price.setText(stocksList.get(position).getPrice());
        holder.SecurityID.setText(stocksList.get(position).getSecurityCode());
        holder.IssuerName.setText(stocksList.get(position).getIssuerName());
    }

    @Override
    public int getItemCount() {
        return stocksList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
      TextView SecurityID ,Price,IssuerName;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            SecurityID=itemView.findViewById(R.id.TV_stockID);
            Price=itemView.findViewById(R.id.TV_price);
            IssuerName=itemView.findViewById(R.id.TV_rv_stockName);
        }
    }
}
