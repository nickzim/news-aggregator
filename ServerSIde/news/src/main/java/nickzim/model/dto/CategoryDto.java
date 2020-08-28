package nickzim.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto implements Comparable<CategoryDto> {

    private String name;

    private Integer count;

    public CategoryDto(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public int compareTo(CategoryDto o) {
        return o.count - this.count;
    }
}
