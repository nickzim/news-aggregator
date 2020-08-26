package nickzim.services.contracts;

import nickzim.model.dto.RssFeedDto;

import java.util.HashSet;

public interface FeedsService {

    HashSet<RssFeedDto> getAllFeeds();
}
