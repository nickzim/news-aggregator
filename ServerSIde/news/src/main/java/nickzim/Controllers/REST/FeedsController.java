package nickzim.Controllers.REST;

import nickzim.Model.DTO.RSSFeedDTO;
import nickzim.Services.Contracts.FeedsService;
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
    FeedsService feedsService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashSet<RSSFeedDTO> getAllFeeds(){
        return feedsService.getAllFeeds();
    }

}
