
package com.nickzim.newsaggregator.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nickzim.newsaggregator.R;
import com.nickzim.newsaggregator.ServerApiInstance;
import com.nickzim.newsaggregator.model.FeedUrl;
import com.nickzim.newsaggregator.serverapi.ServerApi;

import java.util.HashMap;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds_list);

        final ServerApi serverApi = ServerApiInstance.getInstance().getServerApi();

        final ListView feedsList = findViewById(R.id.feedsList);
        final ListView categoriesList = findViewById(R.id.categoriesList);

        final Call<HashSet<FeedUrl>> feeds = serverApi.getAllFeeds();
        feeds.enqueue(new Callback<HashSet<FeedUrl>>() {
            @Override
            public void onResponse(Call<HashSet<FeedUrl>> call, Response<HashSet<FeedUrl>> response) {
                ArrayAdapter<Object> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, response.body().toArray());
                feedsList.setAdapter(adapter);
                feedsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int feedPosition, long l) {
                        FeedUrl feed = (FeedUrl) feedsList.getItemAtPosition(feedPosition);
                        Call<HashMap<String, Integer>> categories = serverApi.getCategoriesFromFeed(feed.getUrl());
                        categories.enqueue(new Callback<HashMap<String, Integer>>() {
                            @Override
                            public void onResponse(Call<HashMap<String, Integer>> call, Response<HashMap<String, Integer>> response) {
                                String[] data = new String[response.body().size() + 1];
                                data[0] = "Все категории";
                                int i = 1;
                                for (String it : response.body().keySet()) {
                                    data[i++] = it + "  " + response.body().get(it).toString();
                                }
                                ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, data);
                                categoriesList.setAdapter(categoryAdapter);
                                categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(FeedsListActivity.this, NewsFeedActivity.class);
                                        FeedUrl feed = (FeedUrl) feedsList.getItemAtPosition(feedPosition);
                                        String category = (String) categoriesList.getItemAtPosition(i);
                                        intent.putExtra("name", feed.getName());
                                        intent.putExtra("url", feed.getUrl());
                                        if (i != 0) {
                                            intent.putExtra("category", category.replaceAll("\\d", "").trim());
                                        } else {
                                            intent.putExtra("category", "null");
                                        }
                                        startActivity(intent);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<HashMap<String, Integer>> call, Throwable t) {
                                System.out.println(t.getMessage());
                                Log.e("error", t.getMessage());
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<HashSet<FeedUrl>> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.e("error", t.getMessage());
            }
        });
    }
}
