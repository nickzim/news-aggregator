package nickzim.Controllers;

import nickzim.Model.DTO.CategoryFeedUrl;
import nickzim.Model.DTO.FeedUrl;
import nickzim.Model.News;
import nickzim.Services.Contracts.CategoryService;
import nickzim.Services.Contracts.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/rest/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<News> getAllNewsForFeed(@RequestBody FeedUrl feedUrl){
        return newsService.getAll(feedUrl.getUrl());
    }

    @RequestMapping(value = "/all/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<News> getAllNewsForFeedForCategory(@RequestBody CategoryFeedUrl feedUrl){
        return newsService.getAllFromCategory(feedUrl.getUrl(), feedUrl.getCategory());
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashSet<String> getAllCategoriesForFeed(@RequestBody FeedUrl feedUrl){
        return categoryService.getAll(feedUrl.getUrl());
    }
}
