package nickzim.services.contracts;

import nickzim.model.dto.CategoryDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface CategoryService {

    HashMap getAllForFeed(String FeedUrl);

    HashMap<String, Integer> getAll();

    ArrayList<CategoryDto> getAllDTOsForFeed(String FeedUrl);
}
