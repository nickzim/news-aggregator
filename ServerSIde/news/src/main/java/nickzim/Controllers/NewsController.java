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
import java.util.List;

@RestController
@RequestMapping("/rest/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<News> getAllNewsForFeed(@RequestParam ("feedUrl") String feedUrl){
        return newsService.getAll(feedUrl);
    }

    /*@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> getAllNewsForFeed(){
        return newsService.getAll("http://tass.ru/rss/v2.xml");
    }*/

    @RequestMapping(value = "/all/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> getAllNewsForFeedForCategory(@RequestBody CategoryFeedUrl feedUrl){
        return newsService.getAllFromCategory(feedUrl.getUrl(), feedUrl.getCategory());
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashSet<String> getAllCategoriesForFeed(@RequestBody FeedUrl feedUrl){
        return categoryService.getAll(feedUrl.getUrl());
    }
}
