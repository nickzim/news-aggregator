import nickzim.Handlers.RSSHandler;
import nickzim.Model.News;
import nickzim.Model.RSSFeed;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class RssTest {

    private URL feedUrl;

    @Before
    public void setUp() throws IOException{
        feedUrl = new URL("http://tass.ru/rss/v2.xml");
    }

    @Test
    public void getRSS() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(feedUrl.openStream()));
        br.lines().forEach(System.out::println);
    }

    @Test
    public void getNewsFromRSS() throws IOException {

        for (News it: RSSHandler.getNewsListFromRSS(feedUrl, "ТАСС")){
            System.out.println(it);
        }

    }

    @Test
    public void getCategoriesFromRSS() throws IOException {
        HashMap<String,Integer> map = RSSHandler.getCategoryMap(RSSHandler.getNewsListFromRSS(feedUrl,"ТАСС"));
        for (String it: map.keySet()){
            System.out.println(it + " : " + map.get(it));
        }
    }
}
