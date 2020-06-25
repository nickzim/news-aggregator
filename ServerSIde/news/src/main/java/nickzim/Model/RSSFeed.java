package nickzim.Model;

import nickzim.Handlers.RSSHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RSSFeed {


    private String name;

    private URL feed;

    private ArrayList<News> newsList;

    private HashMap<String,Integer> categoryMap;

    public RSSFeed(String feedUrl) {
        try {
            feed = new URL(feedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public RSSFeed(String name, String feedUrl) {
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

    public ArrayList<News> getNewsFromCategory(String category){
        ArrayList<News> list = new ArrayList<>();

        for (News it: newsList){

            if (it.getCategory().equals(category)){
                list.add(it);
            }
        }

        return list;

    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public HashMap<String,Integer> getCategoryList() {
        try {
            createNewsList();
        } catch (IOException e) {
            e.getMessage();
        }
        return RSSHandler.getCategoryMap(newsList);
    }


    private void createNewsList() throws IOException{

        newsList = RSSHandler.getNewsListFromRSS(feed,name);

    }



}
