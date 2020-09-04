package nickzim.services.contracts;

import nickzim.model.dto.RssFeedDto;

import java.util.Set;

public interface FeedsService {

    Set<RssFeedDto> getAllFeeds();
}
