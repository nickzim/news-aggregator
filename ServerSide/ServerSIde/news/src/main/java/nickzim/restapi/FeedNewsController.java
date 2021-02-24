package nickzim.restapi;

import lombok.RequiredArgsConstructor;
import nickzim.model.News;
import nickzim.services.contracts.NewsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/news/feed")
@RequiredArgsConstructor
public class FeedNewsController {

    private final NewsService newsService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> getAllNewsForFeed(@RequestParam("feedUrl") String feedUrl){
        return newsService.getAllFromFeed(feedUrl);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> getAllNewsForFeedForCategory(@RequestParam("feedUrl") String feedUrl, @RequestParam("category") String category){
        return newsService.getAllFromCategory(feedUrl,category);
    }
}
