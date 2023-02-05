package nickzim.controller;

import lombok.RequiredArgsConstructor;
import nickzim.service.contract.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/news/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Integer> getAllCategoriesForFeed(@RequestParam ("feedUrl") String feedUrl){
        return categoryService.getAllForFeedUrl(feedUrl);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getAllCategories(){
        return categoryService.getAll();
    }
}
