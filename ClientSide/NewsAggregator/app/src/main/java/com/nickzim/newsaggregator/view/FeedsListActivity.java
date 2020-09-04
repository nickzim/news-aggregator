
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

import java.util.Map;
import java.util.Set;

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

        final Call<Set<FeedUrl>> feeds = serverApi.getAllFeeds();
        feeds.enqueue(new Callback<Set<FeedUrl>>() {
            @Override
            public void onResponse(Call<Set<FeedUrl>> call, Response<Set<FeedUrl>> response) {
                ArrayAdapter<Object> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, response.body().toArray());
                feedsList.setAdapter(adapter);
                feedsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int feedPosition, long l) {
                        FeedUrl feed = (FeedUrl) feedsList.getItemAtPosition(feedPosition);
                        Call<Map<String, Integer>> categories = serverApi.getCategoriesFromFeed(feed.getUrl());
                        categories.enqueue(new Callback<Map<String, Integer>>() {
                            @Override
                            public void onResponse(Call<Map<String, Integer>> call, Response<Map<String, Integer>> response) {
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
                            public void onFailure(Call<Map<String, Integer>> call, Throwable t) {
                                System.out.println(t.getMessage());
                                Log.e("error", t.getMessage());
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<Set<FeedUrl>> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.e("error", t.getMessage());
            }
        });
    }
}
