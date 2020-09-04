package nickzim.services.impls;


import nickzim.model.News;
import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.services.contracts.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public List<News> getAll() {
        List<News> news = new ArrayList<>();
        for (RssFeed it: RssFeedsBase.getFeeds()){
            news.addAll(it.getNewsList());
        }
        return news;
    }

    @Override
    public List<News> getAllFromFeed(String feedUrl) {

        return new RssFeed(feedUrl).getNewsList();
    }

    @Override
    public List<News> getAllFromCategory(String feedUrl, String category) {
        return new RssFeed(feedUrl).getNewsFromCategory(category);
    }
}
