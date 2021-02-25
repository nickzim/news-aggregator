package nickzim.services.impls;

import lombok.RequiredArgsConstructor;
import nickzim.model.dto.NewsFeed;
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

}