package nickzim.controllers.rest;

import nickzim.model.News;
import nickzim.services.contracts.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/news/feed")
public class FeedNewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<News> getAllNewsForFeed(@RequestParam("feedUrl") String feedUrl){
        return newsService.getAllFromFeed(feedUrl);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<News> getAllNewsForFeedForCategory(@RequestParam("feedUrl") String feedUrl, @RequestParam("category") String category){
        return newsService.getAllFromCategory(feedUrl,category);
    }
}
