package nickzim.service.impl;

import lombok.RequiredArgsConstructor;
import nickzim.model.RssSource;
import nickzim.model.dto.CategoryDto;
import nickzim.model.NewsFeed;
import nickzim.repository.NewsFeedRepository;
import nickzim.service.contract.CategoryService;
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
