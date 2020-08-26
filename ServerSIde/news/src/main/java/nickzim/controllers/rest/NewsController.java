package nickzim.controllers.rest;

import nickzim.model.News;
import nickzim.services.contracts.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/rest/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<News> getAllNews(){
        return newsService.getAll();
    }
}
