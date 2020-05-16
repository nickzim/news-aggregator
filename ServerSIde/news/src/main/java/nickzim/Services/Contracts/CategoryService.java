package nickzim.Services.Contracts;

import java.util.HashMap;

public interface CategoryService {

    HashMap getAllForFeed(String FeedUrl);

    HashMap<String, Integer> getAll();
}
