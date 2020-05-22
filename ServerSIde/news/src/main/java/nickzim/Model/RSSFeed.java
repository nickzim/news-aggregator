package nickzim.Model;

import nickzim.Handlers.StringHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //createNewsList();
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
        try {
            createNewsList();
        } catch (IOException e) {
            e.getMessage();
        }
        ArrayList<News> list = new ArrayList<>();

        for (News it: newsList){

            if (it.getCategory().equals(category)){
                list.add(it);
            }
        }

        return list;

    }

    public ArrayList<News> getNewsList() {
        try {
            createNewsList();
        } catch (IOException e) {
            e.getMessage();
        }
        return newsList;
    }

    public HashMap<String,Integer> getCategoryList() {
        try {
            createNewsList();
        } catch (IOException e) {
            e.getMessage();
        }
        HashMap<String,Integer> sortedCategoryMap = new LinkedHashMap<>();
        Stream<Map.Entry<String, Integer>> mapStream = categoryMap.entrySet().stream();
        mapStream.sorted(Map.Entry.comparingByValue()).forEach(e -> sortedCategoryMap.put(e.getKey(), e.getValue()));
        return sortedCategoryMap;
    }


    private void createNewsList() throws IOException{

        newsList =  new ArrayList<>();
        categoryMap = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream()));

        String firstLine = br.readLine();
        if(firstLine != null && firstLine.indexOf("windows-1251") == -1){
            br.close();
            br = new BufferedReader(new InputStreamReader(feed.openStream()));
        } else {
            br.close();
            br = new BufferedReader(new InputStreamReader(feed.openStream(),"Windows-1251"));
        }

        String result = br.lines().collect(Collectors.joining());

        for (String str: StringHandler.deleteCDATAs(result).split(">\\s*<")){


            if (str.trim().startsWith("item")){
                newsList.add(0,new News());
                newsList.get(0).setAgency(name);
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
                String category = StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim();
                categoryMap.put(category,(categoryMap.get(category) == null) ? 1 : categoryMap.get(category) + 1);
            }
            if (str.startsWith("description") && !newsList.isEmpty()){
                newsList.get(0).setDescription(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }

        }

        br.close();
    }



}
