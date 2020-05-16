package nickzim.Model;

import java.util.HashSet;

public class RSSFeedsBase {

    static private HashSet<RSSFeed> feeds;

    static {
        feeds = new HashSet<RSSFeed>(){{
            add(new RSSFeed("ТАСС","http://tass.ru/rss/v2.xml"));
            add(new RSSFeed("Медуза", "https://meduza.io/rss/all"));
            add(new RSSFeed("Sports.ru", "https://www.sports.ru/rss/all_news.xml"));
            add(new RSSFeed("Лента", "https://lenta.ru/rss"));
            add(new RSSFeed("РБК", "http://static.feed.rbc.ru/rbc/logical/footer/news.rss"));
        }};
    }

    static public HashSet<RSSFeed> getFeeds(){
        return feeds;
    }
}
