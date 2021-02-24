package nickzim.restapi;

import lombok.RequiredArgsConstructor;
import nickzim.model.News;
import nickzim.services.contracts.NewsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<News> getAllNews(){
        return newsService.getAll();
    }
}
