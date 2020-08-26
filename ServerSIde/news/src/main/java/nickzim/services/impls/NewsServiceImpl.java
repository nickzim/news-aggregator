package nickzim.services.impls;


import nickzim.model.News;
import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.services.contracts.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public ArrayList<News> getAll() {
        ArrayList<News> news = new ArrayList<>();
        for (RssFeed it: RssFeedsBase.getFeeds()){
            news.addAll(it.getNewsList());
        }
        return news;
    }

    @Override
    public ArrayList<News> getAllFromFeed(String feedUrl) {
        if (!feedUrl.isEmpty()) {
            return new RssFeed(feedUrl).getNewsList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<News> getAllFromCategory(String feedUrl, String category) {
        return new RssFeed(feedUrl).getNewsFromCategory(category);
    }
}
