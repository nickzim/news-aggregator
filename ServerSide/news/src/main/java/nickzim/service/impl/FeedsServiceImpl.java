package nickzim.service.impl;

import lombok.RequiredArgsConstructor;
import nickzim.model.NewsFeed;
import nickzim.repository.NewsFeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedsServiceImpl implements nickzim.service.contract.FeedsService {

    private final NewsFeedRepository repository;

    @Override
    public List<NewsFeed> getAllFeeds() {

        return repository.findAll();
    }

    @Override
    public List<NewsFeed> getAllFeedsByLanguage(String language) {
        return repository.findAllByLanguage(language);
    }

}
