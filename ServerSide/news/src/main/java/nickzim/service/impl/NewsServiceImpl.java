package nickzim.service.impl;


import lombok.RequiredArgsConstructor;
import nickzim.model.News;
import nickzim.model.NewsFeed;
import nickzim.model.RssSource;
import nickzim.repository.NewsFeedRepository;
import nickzim.service.contract.NewsService;
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
            news.addAll(new RssSource(it.getUrl()).getNewsList());
        }
        return news;
    }

    @Override
    public List<News> getAllFromFeed(String feedUrl) {

        return new RssSource(feedUrl).getNewsList();
    }

    @Override
    public List<News> getAllFromCategory(String feedUrl, String category) {
        return new RssSource(feedUrl).getNewsFromCategory(category);
    }
}
