package nickzim.Services.Contracts;

import nickzim.Model.News;

import java.util.ArrayList;

public interface NewsService {

    ArrayList<News> getAll(String feedUrl);

    ArrayList<News> getAllFromCategory(String feedUrl, String category);
}
