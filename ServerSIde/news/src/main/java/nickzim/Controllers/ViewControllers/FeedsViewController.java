package nickzim.Controllers.ViewControllers;

import nickzim.Services.Contracts.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FeedsViewController {

    @Autowired
    FeedsService feedsService;

    @RequestMapping(value = {"/feeds"}, method = RequestMethod.GET)
    public String viewFeeds(Model model) {
        model.addAttribute("feeds", feedsService.getAllFeeds());
        return "feeds";
    }
}
