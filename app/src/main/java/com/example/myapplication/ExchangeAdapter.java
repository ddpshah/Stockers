package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeViewholder> {

    LayoutInflater inflater;
    List<Exchange> exchangeList;
    public ExchangeAdapter(){
    }

    public ExchangeAdapter(Context ctx, List<Exchange> exchangeList){
        this.inflater=LayoutInflater.from(ctx);
        this.exchangeList=exchangeList;
    }

    @NonNull
    @Override
    public ExchangeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.crypto_layout,parent,false);
        return new ExchangeViewholder(view);
    }

    @Override
    public void onBindViewHolder( ExchangeViewholder holder, int position) {

        holder.name.setText(exchangeList.get(position).getName());
        holder.price.setText(exchangeList.get(position).getPrince());
        AnimationDrawable animationDrawable=(AnimationDrawable) holder.CV_cryptoItem.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    @Override
    public int getItemCount() {
        return exchangeList.size();
    }

    public class ExchangeViewholder extends RecyclerView.ViewHolder{
        TextView name,price;
        ConstraintLayout CV_cryptoItem;

        public ExchangeViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.TV_exchange_name);
            price=itemView.findViewById(R.id.TV_exchange_worth_number);
            CV_cryptoItem=itemView.findViewById(R.id.CV_cryptoItem);
        }
    }
}
