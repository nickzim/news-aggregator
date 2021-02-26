package nickzim.services.impls;

import lombok.RequiredArgsConstructor;
import nickzim.model.NewsFeed;
import nickzim.repositories.NewsFeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedsServiceImpl implements nickzim.services.contracts.FeedsService {

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
