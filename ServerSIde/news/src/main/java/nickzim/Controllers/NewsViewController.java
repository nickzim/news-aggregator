package nickzim.Controllers;

import nickzim.Model.News;
import nickzim.Services.Contracts.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewsViewController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String viewFeeds(Model model, @RequestParam("feedUrl") String feedUrl, @RequestParam("category") String category) {
        model.addAttribute("news",  newsService.getAllFromCategory(feedUrl,category));
        return "news";
    }

}
