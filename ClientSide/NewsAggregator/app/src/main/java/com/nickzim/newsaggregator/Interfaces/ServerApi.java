package com.nickzim.newsaggregator.Interfaces;

import com.nickzim.newsaggregator.Model.FeedUrl;
import com.nickzim.newsaggregator.Model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {

    @GET("news/feed")
    Call<List<News>> getNews(@Query("feedUrl") String feedUrl);

    @GET("news/feed/category")
    Call<List<News>> getNewsForCategory(@Query("feedUrl") String feedUrl, @Query("category") String category);

    @GET("news/categories")
    Call<HashMap<String, Integer>> getCategoriesFromFeed(@Query("feedUrl") String feedUrl);

    @GET("news/categories/all")
    Call<HashMap<String, Integer>> getAllCategories();

    @GET("news/all")
    Call<List<News>> getAllNews();

    @GET("feeds")
    Call<HashSet<FeedUrl>> getAllFeeds();



}
