package nickzim.util;

import nickzim.model.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nickzim.util.StringHandleUtils.*;

public class RssHandleUtils {

    public static ArrayList<News> getNewsListFromRSS(URL feed, String name) throws IOException {

        ArrayList<News> newsList = new ArrayList<>();

        String charset;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream()))) {
            charset = isUtf(br.readLine()) ? StandardCharsets.UTF_8.toString() : "Windows-1251";
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream(), charset))) {

            Pattern titlePattern = Pattern.compile("<title>.+</title>");
            Pattern linkPattern = Pattern.compile("<link>.+</link>");
            Pattern datePattern = Pattern.compile("<pubDate>.+</pubDate>");
            Pattern descriptionPattern = Pattern.compile("<description>.+</description>");
            Pattern categoryPattern = Pattern.compile("<category>.+</category>");

            StringBuilder lines = new StringBuilder();
            br.lines().forEach(lines::append);
            for(String it : lines.toString().split("<item>|</item>\\n<item>|</item>")) {

                if (!it.trim().isEmpty()) {
                    News news = new News();

                    Matcher matcher = titlePattern.matcher(it);
                    if (matcher.find()) {
                        news.setTitle(handleString(matcher.group()));
                    }

                    matcher = linkPattern.matcher(it);
                    if (matcher.find()) {
                        news.setLink(handleString(matcher.group()));
                    }

                    matcher = datePattern.matcher(it);
                    if (matcher.find()) {
                        DateTimeFormatter formatter;
                        if (handleString(matcher.group()).endsWith("GMT")) {
                            formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
                        } else {
                            formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                        }
                        LocalDateTime dateTime = LocalDateTime.parse(handleString(matcher.group()), formatter);
                        news.setPubDate(dateTime);
                    }

                    matcher = descriptionPattern.matcher(it);
                    if (matcher.find()) {
                        news.setDescription(handleString(matcher.group()));
                    }

                    matcher = categoryPattern.matcher(it);
                    if (matcher.find()) {
                        news.setCategory(handleString(matcher.group()));
                    }

                    news.setAgency(name);
                    newsList.add(news);
                }
            }
        }

        newsList.remove(0);
        newsList.remove(newsList.size()-1);

        return newsList;
    }

    private static boolean isUtf(String line){
        return !line.contains("windows-1251");
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
