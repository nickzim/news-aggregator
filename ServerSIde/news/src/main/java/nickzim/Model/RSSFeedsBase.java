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
            add(new RSSFeed("Коммерсантъ", "https://www.kommersant.ru/RSS/main.xml" ));
            add(new RSSFeed("Российская газета", "https://rg.ru/xml/index.xml"));
            add(new RSSFeed("Ведомости", "https://www.vedomosti.ru/rss/news"));
            add(new RSSFeed("Советский спорт", "https://www.sovsport.ru/news_rss"));
            add(new RSSFeed("Газета.ру", "https://www.gazeta.ru/export/rss/first.xml"));
            add(new RSSFeed("Интерфакс","https://www.interfax.ru/rss.asp"));
        }};
    }

    static public HashSet<RSSFeed> getFeeds(){
        return feeds;
    }
}
