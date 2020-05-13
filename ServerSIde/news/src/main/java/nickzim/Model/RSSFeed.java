package nickzim.Model;

import nickzim.Handlers.StringHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class RSSFeed {

    private String name;

    private URL feed;

    private ArrayList<News> newsList;

    private HashSet<String> categoryList;

    public RSSFeed(String feedUrl) {
        try {
            feed = new URL(feedUrl);
            createNewsList();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }


    public RSSFeed(String feedUrl, String name) {
        this.name = name;
        try {
            feed = new URL(feedUrl);
            createNewsList();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
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

    public HashSet<String> getCategoryList() {return categoryList;}


    private void createNewsList() throws IOException{

        newsList =  new ArrayList<>();
        categoryList = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream()));
        String result = br.lines().collect(Collectors.joining());

        for (String str: StringHandler.deleteCDATAs(result).split(">\\s*<")){


            if (str.trim().startsWith("item")){
                newsList.add(0,new News());
            }
            if (str.trim().startsWith("title") && !newsList.isEmpty()){
                newsList.get(0).setTitle(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }
            if (str.trim().startsWith("link") && !newsList.isEmpty()){
                newsList.get(0).setLink(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }
            if (str.trim().startsWith("pubDate") && !newsList.isEmpty()){
                newsList.get(0).setPubDate(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }
            if (str.trim().startsWith("category") && !newsList.isEmpty()){
                newsList.get(0).setCategory(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
                categoryList.add(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }
            if (str.startsWith("description") && !newsList.isEmpty()){
                newsList.get(0).setDescription(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }
        }

        br.close();
    }



}
