package com.nickzim.service.contract;

import com.nickzim.model.News;

import java.util.List;

public interface NewsService {

    List<News> getAll();

    List<News> getAllFromFeed(String feedUrl);

    List<News> getAllFromCategory(String feedUrl, String category);
}
