package nickzim.Services.Implements;

import nickzim.Model.DTO.CategoryDTO;
import nickzim.Model.RSSFeed;
import nickzim.Model.RSSFeedsBase;
import nickzim.Services.Contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    @Override
    public ArrayList<CategoryDTO> getAllDTOsForFeed(String FeedUrl) {
        ArrayList<CategoryDTO> categories = new ArrayList<>();
        HashMap<String,Integer> categoriesMap = getAllForFeed(FeedUrl);
        for (String it: categoriesMap.keySet()){
            try {
                categories.add(new CategoryDTO(URLEncoder.encode(it,"UTF8"), it , categoriesMap.get(it)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
}
