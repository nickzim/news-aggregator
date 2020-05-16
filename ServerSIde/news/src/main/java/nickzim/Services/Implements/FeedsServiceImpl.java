package nickzim.Services.Implements;

import nickzim.Model.DTO.RSSFeedDTO;
import nickzim.Model.RSSFeed;
import nickzim.Model.RSSFeedsBase;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class FeedsServiceImpl implements nickzim.Services.Contracts.FeedsService {

    @Override
    public HashSet<RSSFeedDTO> getAllFeeds() {
        HashSet<RSSFeedDTO> feedsSet = new HashSet<>();
        for (RSSFeed it: RSSFeedsBase.getFeeds()){
            feedsSet.add(new RSSFeedDTO(it.getName(),it.getFeed().toString()));
        }
        return feedsSet;
    }

}
