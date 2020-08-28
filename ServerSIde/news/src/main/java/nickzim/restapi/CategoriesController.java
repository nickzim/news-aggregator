package nickzim.restapi;

import nickzim.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/rest/news/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashMap<String,Integer> getAllCategoriesForFeed(@RequestParam ("feedUrl") String feedUrl){
        return categoryService.getAllForFeedUrl(feedUrl);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HashMap<String, Integer> getAllCategories(){
        return categoryService.getAll();
    }
}
