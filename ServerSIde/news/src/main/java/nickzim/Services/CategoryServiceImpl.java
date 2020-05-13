package nickzim.Services;

import nickzim.Model.RSSFeed;
import nickzim.Services.Contracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public HashSet<String> getAll(String FeedUrl) {
        return new RSSFeed(FeedUrl).getCategoryList();
    }
}
