package nickzim.Services.Contracts;

import nickzim.Model.DTO.RSSFeedDTO;

import java.util.HashSet;

public interface FeedsService {

    HashSet<RSSFeedDTO> getAllFeeds();
}
