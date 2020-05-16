package nickzim.Services.Contracts;

import java.util.ArrayList;
import java.util.HashSet;

public interface CategoryService {

    HashSet<String> getAllForFeed(String FeedUrl);

    HashSet<String> getAll();
}
