package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.io.IOException;

public class DisplayingChartWeb extends AppCompatActivity {
TextView getText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getText=findViewById(R.id.TV_getText);
        setContentView(R.layout.activity_displaying_chart_web);

        description_webscrape dw=new description_webscrape();
        dw.execute();
    }

    private class description_webscrape extends AsyncTask<Void,Void,Void> {
        String theDescription;

        @Override
        protected Void doInBackground(Void... voids) {
            org.jsoup.nodes.Document document=null;
            getText=findViewById(R.id.TV_getText);
            try {
                document= Jsoup.connect("https://www.google.com/search?q=mesozoic+era").get();
            } catch (IOException e) {
                Log.e("This is in catch"," ");
                e.printStackTrace();
            }
            org.jsoup.select.Elements elements=document.getElementsByClass("hgKElc");
            theDescription=elements.text().toString();

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            getText.setText(theDescription);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}