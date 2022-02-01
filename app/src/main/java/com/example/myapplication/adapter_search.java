package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class adapter_search extends RecyclerView.Adapter<adapter_search.MyViewHolder> {

        public static ArrayList<recycler_search> list;
        private MyViewHolder.OnClickOrder mOrderPageListener;
        public adapter_search(ArrayList<recycler_search> list,MyViewHolder.OnClickOrder mOrderPageListener){
            this.list=list;
            this.mOrderPageListener = mOrderPageListener;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_autocomplete_itemview,parent,false);
            return new MyViewHolder(view,mOrderPageListener);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.id.setText(list.get(position).getIssuer());
            holder.desc.setText(list.get(position).getSecurity_Name());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView id,desc;
            public OnClickOrder mOrderPageListener;
            public MyViewHolder(@NonNull View itemView,OnClickOrder m1OrderPageListener) {
                super(itemView);
                id=itemView.findViewById(R.id.Issuer_name);
                desc=itemView.findViewById(R.id.ticker);
                desc.setTextColor(Color.WHITE);
                mOrderPageListener=m1OrderPageListener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                mOrderPageListener.getIssuerName(list.get(getAbsoluteAdapterPosition()).getSecurity_Name());
            }
            public static interface OnClickOrder {
                void getIssuerName(String issuer);

            }
        }
    }

