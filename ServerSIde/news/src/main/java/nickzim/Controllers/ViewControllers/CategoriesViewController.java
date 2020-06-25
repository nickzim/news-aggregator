package nickzim.Controllers.ViewControllers;

import nickzim.Services.Contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class CategoriesViewController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/categories"}, method = RequestMethod.GET)
    public String viewFeeds(Model model, @RequestParam ("feedUrl") String feedUrl) {
        model.addAttribute("categories",  categoryService.getAllDTOsForFeed(feedUrl));
        return "categories";
    }
}
