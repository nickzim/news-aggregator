package nickzim.model;

import lombok.Getter;
import lombok.Setter;
import nickzim.utils.RssHandleUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RssSource {

    private String name;

    private URL feed;

    private List<News> newsList;

    private Map<String,Integer> categoryMap;

    public RssSource(String feedUrl) {
        try {
            feed = new URL(feedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void downloadRssData(){

        try {

            if (newsList == null) {
                newsList = RssHandleUtils.getNewsListFromRSS(feed, name);
            }
            if (categoryMap == null) {
                categoryMap = RssHandleUtils.getCategoryMap(newsList);
            }

        } catch (IOException e) {
            newsList = new ArrayList<>();
            categoryMap = new HashMap<>();

        }

    }

    public List<News> getNewsList() {
        downloadRssData();
        return newsList;
    }

    public Map<String,Integer> getCategoryList() {
        downloadRssData();

        return categoryMap;
    }

    public List<News> getNewsFromCategory(String category){
        ArrayList<News> newsFromCategoryList = new ArrayList<>();

        downloadRssData();

        for (News it: newsList){

            if (it.getCategory() != null && it.getCategory().equals(category)){
                newsFromCategoryList.add(it);
            }
        }

        return newsFromCategoryList;

    }





}
