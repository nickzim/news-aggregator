package nickzim.services.impls;

import lombok.RequiredArgsConstructor;
import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.model.dto.CategoryDto;
import nickzim.model.dto.NewsFeed;
import nickzim.repositories.NewsFeedRepository;
import nickzim.services.contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final NewsFeedRepository repository;

    @Override
    public Map<String,Integer> getAllForFeedUrl(String feedUrl) {
        return new RssFeed(feedUrl).getCategoryList();
    }


    @Override
    public Map<String, Integer> getAll() {

        Map<String,Integer> categoriesMap = new HashMap<>();
        for (NewsFeed it: repository.findAll()){
            categoriesMap.putAll(new RssFeed(it.getUrl()).getCategoryList());
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
