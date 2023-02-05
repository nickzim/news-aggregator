package com.nickzim.util;

import com.nickzim.enums.Charset;
import com.nickzim.model.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RssHandleUtils {

    public static List<News> getNewsListFromRSS(URL feed, String name) throws IOException {

        List<News> newsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream(), getCharset(feed)))) {

            Pattern titlePattern = Pattern.compile("<title>.+</title>");
            Pattern linkPattern = Pattern.compile("<link>.+</link>");
            Pattern datePattern = Pattern.compile("<pubDate>.+</pubDate>");
            Pattern descriptionPattern = Pattern.compile("<description>.+</description>");
            Pattern categoryPattern = Pattern.compile("<category>.+</category>");

            StringBuilder lines = new StringBuilder();
            br.lines().forEach(lines::append);

            for (String it : lines.toString().split("<item>|</item>\\n<item>|</item>")) {

                if (!it.trim().isEmpty()) {
                    News news = new News();

                    Matcher matcher = titlePattern.matcher(it);
                    if (matcher.find()) {
                        news.setTitle(StringHandleUtils.handleString(matcher.group()));
                    }

                    matcher = linkPattern.matcher(it);
                    if (matcher.find()) {
                        news.setLink(StringHandleUtils.handleString(matcher.group()));
                    }

                    matcher = datePattern.matcher(it);
                    if (matcher.find()) {
                        news.setPubDate(DateUtils.getNewsPubDate(matcher.group()));
                    }

                    matcher = descriptionPattern.matcher(it);
                    if (matcher.find()) {
                        news.setDescription(StringHandleUtils.handleString(matcher.group()));
                    }

                    matcher = categoryPattern.matcher(it);
                    if (matcher.find()) {
                        news.setCategory(StringHandleUtils.handleString(matcher.group()));
                    }

                    news.setAgency(name);
                    newsList.add(news);
                }
            }
        }

        if (!newsList.isEmpty()){
            newsList.remove(0);
            newsList.remove(newsList.size() - 1);
        }

        return newsList;
    }

    public static Map<String, Integer> getCategoryMap(List<News> newsList){

        Map<String,Integer> categoriesMap = new HashMap<>();

        for (News it: newsList){
            if (it.getCategory() != null) {
                categoriesMap.put(it.getCategory(), categoriesMap.getOrDefault(it.getCategory(), 0) + 1);
            }
        }

        return categoriesMap;
    }

    private static String getCharset(URL feed) throws IOException{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(feed.openStream()))) {
            String charsetRssLine = br.readLine();
            if (charsetRssLine.contains("windows-1251")) {
                return Charset.WINDOWS_1251.getCharset();
            } else {
                return Charset.UTF_8.getCharset();
            }
        }
    }
}
