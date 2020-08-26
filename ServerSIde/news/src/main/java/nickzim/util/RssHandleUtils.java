package nickzim.util;

import nickzim.model.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RssHandleUtils {

    public static ArrayList<News> getNewsListFromRSS(URL feed, String name) throws IOException {

        ArrayList<News> newsList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream()));

        StringBuilder firstLine = new StringBuilder(br.readLine());
        if(firstLine != null && firstLine.indexOf("windows-1251") == -1){
            br.close();
            br = new BufferedReader(new InputStreamReader(feed.openStream()));
        } else {
            br.close();
            br = new BufferedReader(new InputStreamReader(feed.openStream(),"Windows-1251"));
        }

        Arrays.stream(StringHandleUtils.deleteCDATAs(br.lines().collect(Collectors.joining())).split(">\\s*<")).forEach(str -> {

            str = str.trim();

            if (str.startsWith("item")){
                newsList.add(0,new News());
                newsList.get(0).setAgency(name);
            }
            if (str.startsWith("title") && !newsList.isEmpty()){
                newsList.get(0).setTitle(StringHandleUtils.deleteTags(StringHandleUtils.deleteQuotes(str)).trim());
            }
            if (str.startsWith("link") && !newsList.isEmpty()){
                newsList.get(0).setLink(StringHandleUtils.deleteTags(StringHandleUtils.deleteQuotes(str)).trim());
            }
            if (str.startsWith("pubDate") && !newsList.isEmpty()){
                newsList.get(0).setPubDate(StringHandleUtils.deleteTags(StringHandleUtils.deleteQuotes(str)).trim());
            }
            if (str.startsWith("category") && !newsList.isEmpty()){
                newsList.get(0).setCategory(StringHandleUtils.deleteTags(StringHandleUtils.deleteQuotes(str)).trim());
            }
            if (str.startsWith("description") && !newsList.isEmpty()){
                newsList.get(0).setDescription(StringHandleUtils.deleteTags(StringHandleUtils.deleteQuotes(str)).trim());
            }

        });

        br.close();

        return newsList;
    }

    public static HashMap<String, Integer> getCategoryMap(ArrayList<News> newsList){
        HashMap<String,Integer> categoriesMap = new HashMap<>();
        for (News it: newsList){
            if (it.getCategory() != null) {
                categoriesMap.put(it.getCategory(), categoriesMap.getOrDefault(it.getCategory(), 0) + 1);
            }
        }
        return categoriesMap;
    }
}
