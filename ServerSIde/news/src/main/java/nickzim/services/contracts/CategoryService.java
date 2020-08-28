package nickzim.services.contracts;

import nickzim.model.dto.CategoryDto;

import java.util.ArrayList;
import java.util.HashMap;

public interface CategoryService {

    HashMap getAllForFeedUrl(String feedUrl);

    HashMap<String, Integer> getAll();

    ArrayList<CategoryDto> getAllDTOsForFeedUrl(String feedUrl);

}
