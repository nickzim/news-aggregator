package nickzim.services.contracts;

import nickzim.model.News;

import java.util.ArrayList;

public interface NewsService {

    ArrayList<News> getAll();

    ArrayList<News> getAllFromFeed(String feedUrl);

    ArrayList<News> getAllFromCategory(String feedUrl, String category);
}
