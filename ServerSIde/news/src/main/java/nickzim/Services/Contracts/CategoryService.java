package nickzim.Services.Contracts;

import nickzim.Model.DTO.CategoryDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface CategoryService {

    HashMap getAllForFeed(String FeedUrl);

    HashMap<String, Integer> getAll();

    ArrayList<CategoryDTO> getAllDTOsForFeed(String FeedUrl);
}
