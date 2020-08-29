package nickzim.model.database;

import nickzim.model.RssFeed;
import nickzim.model.dto.RssFeedDto;

import java.util.HashSet;

public class RssFeedsBase {

    static private HashSet<RssFeed> feeds;

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
        }};
    }

    static public HashSet<RssFeed> getFeeds(){
        return feeds;
    }

    static public RssFeedDto getRssFeedDto(String name){
        for (RssFeed it: feeds){
            if (it.getName().equals(name)){
                return new RssFeedDto(it.getName(), it.getFeed().toString());
            }
        }
        return null;
    }
}
