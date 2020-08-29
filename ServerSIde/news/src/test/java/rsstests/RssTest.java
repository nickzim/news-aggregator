package rsstests;

import nickzim.utils.RssHandleUtils;
import nickzim.model.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class RssTest {

    private URL feedUrl;

    @Before
    public void setUp() throws IOException{
        feedUrl = new URL("https://www.kommersant.ru/RSS/main.xml");
    }

    @Test
    public void getNewsFromRSS() throws IOException {

        List<News> newsList = RssHandleUtils.getNewsListFromRSS(feedUrl, "ТАСС");

        for (News it: newsList){
            System.out.println(it);
        }

        Assert.assertNotNull(newsList);
        Assert.assertNotSame(0, newsList.size());


    }

    @Test
    public void getCategoriesFromRSS() throws IOException {
        HashMap<String,Integer> map = RssHandleUtils.getCategoryMap(RssHandleUtils.getNewsListFromRSS(feedUrl,"ТАСС"));

        for (String it: map.keySet()){
            System.out.println(it + " : " + map.get(it));
        }

        Assert.assertNotNull(map);
        Assert.assertNotSame(0,map.size());
    }
}
