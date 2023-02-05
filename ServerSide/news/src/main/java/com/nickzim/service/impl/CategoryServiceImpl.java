package com.nickzim.service.impl;

import com.nickzim.model.NewsFeed;
import com.nickzim.model.dto.CategoryDto;
import com.nickzim.service.contract.CategoryService;
import lombok.RequiredArgsConstructor;
import com.nickzim.model.RssSource;
import com.nickzim.repository.NewsFeedRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final NewsFeedRepository repository;

    @Override
    public Map<String,Integer> getAllForFeedUrl(String feedUrl) {
        return new RssSource(feedUrl).getCategoryList();
    }


    @Override
    public Map<String, Integer> getAll() {

        Map<String,Integer> categoriesMap = new HashMap<>();
        for (NewsFeed it: repository.findAll()){
            categoriesMap.putAll(new RssSource(it.getUrl()).getCategoryList());
        }
        return categoriesMap;

    }

    @Override
    public List<CategoryDto> getAllDTOsForFeedUrl(String feedUrl) {

        Map<String,Integer> categoriesMap = getAllForFeedUrl(feedUrl);

        if (categoriesMap.isEmpty()){
            return Collections.emptyList();
        }

        List<CategoryDto> categories = new ArrayList<>();

        for (String it: categoriesMap.keySet()){
            categories.add(new CategoryDto(it , categoriesMap.get(it)));
        }
        categories.sort((o1, o2) -> o2.getCount() - o1.getCount());

        return categories;
    }


}
