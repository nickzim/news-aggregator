package nickzim.Handlers;

import nickzim.Model.News;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RSSHandler {

    public static ArrayList<News> getNewsListFromRSS(URL feed, String name) throws IOException {

        ArrayList<News> newsList = new ArrayList<>();

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
            }
            if (str.startsWith("description") && !newsList.isEmpty()){
                newsList.get(0).setDescription(StringHandler.deleteTags(StringHandler.deleteQuotes(str)).trim());
            }

        }

        br.close();

        return newsList;
    }

    public static HashMap<String, Integer> getCategoryMap(ArrayList<News> newsList){
        HashMap<String,Integer> categoriesMap = new HashMap<>();
        for (News it: newsList){
            String category = it.getCategory();
            categoriesMap.put(category, categoriesMap.get(category) == null ? 1 : categoriesMap.get(category).intValue() + 1);
        }
        return categoriesMap;
    }
}
