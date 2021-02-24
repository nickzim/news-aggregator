package nickzim.model.database;

import nickzim.model.RssFeed;

import java.util.HashSet;
import java.util.Set;

public class RssFeedsBase {

    static private Set<RssFeed> feeds;

    static {
        feeds = new HashSet<RssFeed>(){{
            add(new RssFeed("ТАСС","http://tass.ru/rss/v2.xml"));
            add(new RssFeed("Медуза", "https://meduza.io/rss/all"));
            add(new RssFeed("Sports.ru", "https://www.sports.ru/rss/all_news.xml"));
            add(new RssFeed("Лента", "https://lenta.ru/rss"));
            add(new RssFeed("РБК", "http://static.feed.rbc.ru/rbc/logical/footer/news.rss"));
            add(new RssFeed("Коммерсантъ", "https://www.kommersant.ru/RSS/main.xml" ));
            add(new RssFeed("Российская газета", "https://rg.ru/xml/index.xml"));
            add(new RssFeed("Ведомости", "https://www.vedomosti.ru/rss/news"));
            add(new RssFeed("Газета.ру", "https://www.gazeta.ru/export/rss/first.xml"));
            add(new RssFeed("Интерфакс","https://www.interfax.ru/rss.asp"));
            add(new RssFeed("CNN", "http://rss.cnn.com/rss/edition.rss"));
            add(new RssFeed("Al Jazeera","https://www.aljazeera.com/xml/rss/all.xml"));
            add(new RssFeed("Russia Today","https://russian.rt.com/rss"));
            add(new RssFeed("The New York Times", "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml"));
            add(new RssFeed("BBC","http://feeds.bbci.co.uk/news/rss.xml"));
            add(new RssFeed("Sky Sports","https://www.skysports.com/rss/12040"));
        }};
    }

    static public Set<RssFeed> getFeeds(){
        return feeds;
    }

}
