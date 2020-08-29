package nickzim.model;

import nickzim.utils.RssHandleUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RssFeed {


    private String name;

    private URL feed;

    private ArrayList<News> newsList;

    private HashMap<String,Integer> categoryMap;

    public RssFeed(String feedUrl) {
        try {
            feed = new URL(feedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public RssFeed(String name, String feedUrl) {
        this.name = name;
        try {
            feed = new URL(feedUrl);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public URL getFeed() {
        return feed;
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

    public ArrayList<News> getNewsList() {
        downloadRssData();
        return newsList;
    }

    public HashMap<String,Integer> getCategoryList() {
        downloadRssData();

        return categoryMap;
    }

    public ArrayList<News> getNewsFromCategory(String category){
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
