package nickzim.services.impls;

import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.model.dto.CategoryDto;
import nickzim.services.contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Map<String,Integer> getAllForFeedUrl(String feedUrl) {
        return new RssFeed(feedUrl).getCategoryList();
    }


    @Override
    public Map<String, Integer> getAll() {

        Map<String,Integer> categoriesMap = new HashMap<>();
        for (RssFeed it: RssFeedsBase.getFeeds()){
            categoriesMap.putAll(it.getCategoryList());
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
