package com.nickzim.newsaggregator.serverapi;

import com.nickzim.newsaggregator.model.FeedUrl;
import com.nickzim.newsaggregator.model.News;

import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {

    @GET("news/feed")
    Call<List<News>> getNews(@Query("feedUrl") String feedUrl);

    @GET("news/feed/category")
    Call<List<News>> getNewsForCategory(@Query("feedUrl") String feedUrl, @Query("category") String category);

    @GET("news/categories")
    Call<Map<String, Integer>> getCategoriesFromFeed(@Query("feedUrl") String feedUrl);

    @GET("news/categories/all")
    Call<Map<String, Integer>> getAllCategories();

    @GET("news/all")
    Call<List<News>> getAllNews();

    @GET("feeds")
    Call<Set<FeedUrl>> getAllFeeds();



}
