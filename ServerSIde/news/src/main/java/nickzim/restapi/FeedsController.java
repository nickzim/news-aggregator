package nickzim.restapi;

import nickzim.model.dto.RssFeedDto;
import nickzim.services.contracts.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;



@RestController
@RequestMapping("/rest/feeds")
public class FeedsController {

    @Autowired
    private FeedsService feedsService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashSet<RssFeedDto> getAllFeeds(){
        return feedsService.getAllFeeds();
    }

}
