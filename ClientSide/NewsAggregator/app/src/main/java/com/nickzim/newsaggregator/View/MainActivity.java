package com.nickzim.newsaggregator.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nickzim.newsaggregator.Interfaces.ServerApi;
import com.nickzim.newsaggregator.Model.News;
import com.nickzim.newsaggregator.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView listView = findViewById(R.id.list);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/rest/news/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<News>> newsList = serverApi.getNews();

        newsList.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                ArrayAdapter<News> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, response.body());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                String failMsg[] = {"Ошибка при подключении к серверу", t.getMessage()};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, failMsg);
                listView.setAdapter(adapter);
            }
        });


    }
}
