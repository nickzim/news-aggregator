package com.nickzim.service.contract;

import com.nickzim.model.NewsFeed;

import java.util.List;

public interface FeedsService {

    List<NewsFeed> getAllFeeds();

    List<NewsFeed> getAllFeedsByLanguage(String language);
}
