package nickzim.Services;


import nickzim.Model.News;
import nickzim.Model.RSSFeed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public ArrayList<News> getAll(String feedUrl) {
        return new RSSFeed(feedUrl).getNewsList();
    }
}
