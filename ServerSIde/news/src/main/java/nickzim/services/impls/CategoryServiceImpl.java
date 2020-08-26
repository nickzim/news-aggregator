package nickzim.services.impls;

import nickzim.model.dto.CategoryDto;
import nickzim.model.database.RssFeedsBase;
import nickzim.model.RssFeed;
import nickzim.services.contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public HashMap<String,Integer> getAllForFeed(String FeedUrl) {
        return new RssFeed(FeedUrl).getCategoryList();
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
    public ArrayList<CategoryDto> getAllDTOsForFeed(String FeedUrl) {

        HashMap<String,Integer> categoriesMap = getAllForFeed(FeedUrl);

        if (categoriesMap.isEmpty()){
            return new ArrayList<>();
        }

        ArrayList<CategoryDto> categories = new ArrayList<>();

        for (String it: categoriesMap.keySet()){
            try {
                categories.add(new CategoryDto(URLEncoder.encode(it,"UTF8"), it , categoriesMap.get(it)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
}
