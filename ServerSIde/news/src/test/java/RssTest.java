import nickzim.Handlers.RSSHandler;
import nickzim.Model.News;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class RssTest {

    @Test
    public void getRSS() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://meduza.io/rss/all").openStream()));
        br.lines().forEach(System.out::println);
    }

    @Test
    public void getNewsFromRSS() throws IOException {

        for (News it: RSSHandler.getNewsListFromRSS(new URL("https://meduza.io/rss/all"),"ТАСС")){
            System.out.println(it);
        }

    }

    @Test
    public void getCategoriesFromRSS() throws IOException {
        HashMap<String,Integer> map = RSSHandler.getCategoryMap(RSSHandler.getNewsListFromRSS(new URL("https://meduza.io/rss/all"),"ТАСС"));
        for (String it: map.keySet()){
            System.out.println(it + " : " + map.get(it));
        }
    }
}
