package com.nickzim.newsaggregator.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nickzim.newsaggregator.Interfaces.ServerApi;
import com.nickzim.newsaggregator.Model.News;
import com.nickzim.newsaggregator.R;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    HashMap<String,String> feedsMap = new HashMap<String,String>() {{
        put("ТАСС","http://tass.ru/rss/v2.xml");
        put("Медуза", "https://meduza.io/rss/all");
        put("Sports.ru", "https://www.sports.ru/rss/all_news.xml");
        put("Лента", "https://lenta.ru/rss");
        put("РБК", "http://static.feed.rbc.ru/rbc/logical/footer/news.rss");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/rest/news/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final ServerApi serverApi = retrofit.create(ServerApi.class);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, feedsMap.keySet().toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ListView listView = findViewById(R.id.list);
        final Spinner agencyList = findViewById(R.id.agencyList);

        agencyList.setAdapter(adapter);
        agencyList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Call<List<News>> newsList = serverApi.getNews(feedsMap.get(adapter.getItem(position)));
                newsList.enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                        ArrayAdapter<News> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, response.body());
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                News news = (News) listView.getItemAtPosition(i);
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                                startActivity(browserIntent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<News>> call, Throwable t) {
                        String failMsg[] = {"Ошибка при подключении к серверу", t.getMessage()};
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, failMsg);
                        listView.setAdapter(adapter);
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



    }
}
