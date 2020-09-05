package nickzim.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto implements Comparable<CategoryDto> {

    private String name;

    private Integer count;

    @Override
    public int compareTo(CategoryDto o) {
        return o.count - this.count;
    }
}
