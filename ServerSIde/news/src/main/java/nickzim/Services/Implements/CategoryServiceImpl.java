package nickzim.Services.Implements;

import nickzim.Model.RSSFeed;
import nickzim.Model.RSSFeedsBase;
import nickzim.Services.Contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public HashMap<String,Integer> getAllForFeed(String FeedUrl) {
        return new RSSFeed(FeedUrl).getCategoryList();
    }

    @Override
    public HashMap<String, Integer> getAll() {
        HashMap<String,Integer> categoriesMap = new HashMap<>();
        for (RSSFeed it: RSSFeedsBase.getFeeds()){
            categoriesMap.putAll(it.getCategoryList());
        }
        return categoriesMap;
    }
}
