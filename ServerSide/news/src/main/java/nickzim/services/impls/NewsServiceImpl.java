package nickzim.services.impls;


import lombok.RequiredArgsConstructor;
import nickzim.model.News;
import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import nickzim.model.dto.NewsFeed;
import nickzim.repositories.NewsFeedRepository;
import nickzim.services.contracts.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsFeedRepository repository;

    @Override
    public List<News> getAll() {

        List<News> news = new ArrayList<>();
        for (NewsFeed it: repository.findAll()){
            news.addAll(new RssFeed(it.getUrl()).getNewsList());
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
