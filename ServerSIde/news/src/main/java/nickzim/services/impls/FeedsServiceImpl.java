package nickzim.services.impls;

import nickzim.model.dto.RssFeedDto;
import nickzim.model.RssFeed;
import nickzim.model.database.RssFeedsBase;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class FeedsServiceImpl implements nickzim.services.contracts.FeedsService {

    @Override
    public HashSet<RssFeedDto> getAllFeeds() {
        HashSet<RssFeedDto> feedsSet = new HashSet<>();
        for (RssFeed it: RssFeedsBase.getFeeds()){
            feedsSet.add(new RssFeedDto(it.getName(),it.getFeed().toString()));
        }
        return feedsSet;
    }

}
