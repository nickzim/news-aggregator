package nickzim.services.contracts;

import nickzim.model.NewsFeed;

import java.util.List;

public interface FeedsService {

    List<NewsFeed> getAllFeeds();
}
