package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {
    // API key: 693b95cb550a4cb5ac004aa32cc76564
    private RecyclerView newsRV,categoryRV;
    private ProgressBar progressBar;
    private ArrayList<Articles> articlesArrayList;

    private NewsRVAdapter newsRVAdapter;


    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news, container, false);

        newsRV=view.findViewById(R.id.idNewsRV);
        progressBar=view.findViewById(R.id.idPBLoading);
        articlesArrayList=new ArrayList<>();
        newsRVAdapter=new NewsRVAdapter(articlesArrayList,getContext());
        newsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRV.setAdapter(newsRVAdapter);
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
        return view;
    }



    private void getNews(String category){
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear(); // clears and add specfic categories
        String categoryURL="https://newsapi.org/v2/top-headlines?country=in&category="+"business"+"&apiKey=693b95cb550a4cb5ac004aa32cc76564";
        String url=categoryURL;
        String BASE_URL="https://newsapi.org/";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI= retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call= retrofitAPI.getAllNews(url);
        }
        else{
            call=retrofitAPI.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal=response.body();
                progressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getContent(),articles.get(i).getUrl()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(getContext(),"Failure to get API",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
