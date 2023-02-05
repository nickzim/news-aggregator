package com.nickzim.service.impl;

import com.nickzim.model.NewsFeed;
import com.nickzim.service.contract.FeedsService;
import lombok.RequiredArgsConstructor;
import com.nickzim.repository.NewsFeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedsServiceImpl implements FeedsService {

    private final NewsFeedRepository repository;

    @Override
    public List<NewsFeed> getAllFeeds() {

        return repository.findAll();
    }

    @Override
    public List<NewsFeed> getAllFeedsByLanguage(String language) {
        return repository.findAllByLanguage(language);
    }

}
