package nickzim.services.contracts;

import nickzim.model.dto.NewsFeed;

import java.util.List;
import java.util.Set;

public interface FeedsService {

    List<NewsFeed> getAllFeeds();
}
