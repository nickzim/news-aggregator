package com.nickzim.newsaggregator.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nickzim.newsaggregator.R;
import com.nickzim.newsaggregator.ServerApiInstance;
import com.nickzim.newsaggregator.adapters.NewsAdapter;
import com.nickzim.newsaggregator.model.News;
import com.nickzim.newsaggregator.serverapi.ServerApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Intent intent = getIntent();

        ServerApi serverApi = ServerApiInstance.getInstance().getServerApi();


        final RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final Context context = this;

        final Call<List<News>> newsList;
        if (intent.getStringExtra("category").equals("null")) {
            newsList = serverApi.getNews(intent.getStringExtra("url"));
        } else {
            newsList = serverApi.getNewsForCategory(intent.getStringExtra("url"), intent.getStringExtra("category"));
            System.out.println(intent.getStringExtra("category"));
        }
        newsList.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                NewsAdapter adapter = new NewsAdapter(response.body(), context);
                adapter.setListener(new NewsAdapter.onClickListener() {
                    @Override
                    public void onClick(News news) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                        startActivity(browserIntent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                String failMsg[] = {"Ошибка при подключении к серверу", t.getMessage()};
                NewsAdapter adapter = new NewsAdapter(Collections.EMPTY_LIST, context);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
