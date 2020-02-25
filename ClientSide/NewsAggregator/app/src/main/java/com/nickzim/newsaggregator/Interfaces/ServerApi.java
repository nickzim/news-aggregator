package com.nickzim.newsaggregator.Interfaces;

import com.nickzim.newsaggregator.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("all")
    Call<List<News>> getNews();

    @GET("test")
    Call<String> test();

}
