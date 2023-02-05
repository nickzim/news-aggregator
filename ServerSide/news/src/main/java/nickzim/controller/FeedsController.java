package nickzim.controller;

import lombok.RequiredArgsConstructor;
import nickzim.model.NewsFeed;
import nickzim.service.contract.FeedsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest/feeds")
@RequiredArgsConstructor
public class FeedsController {

    private final FeedsService feedsService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsFeed> getAllFeeds(){
        return feedsService.getAllFeeds();
    }

}
