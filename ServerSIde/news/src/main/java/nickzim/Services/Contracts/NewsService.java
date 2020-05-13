package nickzim.Services;

import nickzim.Model.News;

import java.util.ArrayList;

public interface NewsService {

    ArrayList<News> getAll(String feedUrl);
}
