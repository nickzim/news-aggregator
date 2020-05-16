package nickzim.Services.Implements;


import nickzim.Model.News;
import nickzim.Model.RSSFeed;
import nickzim.Services.Contracts.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public ArrayList<News> getAll() {
        return null;
    }

    @Override
    public ArrayList<News> getAllFromFeed(String feedUrl) {
        if (!feedUrl.isEmpty()) {
            return new RSSFeed(feedUrl).getNewsList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<News> getAllFromCategory(String feedUrl, String category) {
        return new RSSFeed(feedUrl).getNewsFromCategory(category);
    }
}
