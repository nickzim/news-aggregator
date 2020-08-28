package nickzim.services.impls;

import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.model.dto.CategoryDto;
import nickzim.services.contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public HashMap<String,Integer> getAllForFeedUrl(String feedUrl) {
        return new RssFeed(feedUrl).getCategoryList();
    }


    @Override
    public HashMap<String, Integer> getAll() {
        HashMap<String,Integer> categoriesMap = new HashMap<>();
        for (RssFeed it: RssFeedsBase.getFeeds()){
            categoriesMap.putAll(it.getCategoryList());
        }
        return categoriesMap;
    }

    @Override
    public ArrayList<CategoryDto> getAllDTOsForFeedUrl(String feedUrl) {

        HashMap<String,Integer> categoriesMap = getAllForFeedUrl(feedUrl);

        if (categoriesMap.isEmpty()){
            return new ArrayList<>();
        }

        ArrayList<CategoryDto> categories = new ArrayList<>();

        for (String it: categoriesMap.keySet()){
            categories.add(new CategoryDto(it , categoriesMap.get(it)));
        }
        categories.sort((o1, o2) -> o2.getCount() - o1.getCount());

        return categories;
    }


}
