package nickzim.Services;


import nickzim.Model.News;
import nickzim.Model.RSSFeed;
import nickzim.Services.Contracts.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public ArrayList<News> getAll(String feedUrl) {
        return new RSSFeed(feedUrl).getNewsList();
    }

    @Override
    public ArrayList<News> getAllFromCategory(String feedUrl, String category) {
        return new RSSFeed(feedUrl).getNewsFromCategory(category);
    }
}
