package com.nickzim.newsaggregator.Interfaces;

import com.nickzim.newsaggregator.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {

    @GET("all")
    Call<List<News>> getNews(@Query("feedUrl") String feedUrl);

    @GET("all/categories")
    Call<List<String>> getCategories(@Query("feedUrl") String feedUrl);

}
