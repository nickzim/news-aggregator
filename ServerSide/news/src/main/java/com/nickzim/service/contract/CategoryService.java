package com.nickzim.service.contract;

import com.nickzim.model.dto.CategoryDto;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Map<String, Integer> getAllForFeedUrl(String feedUrl);

    Map<String, Integer> getAll();

    List<CategoryDto> getAllDTOsForFeedUrl(String feedUrl);

}
