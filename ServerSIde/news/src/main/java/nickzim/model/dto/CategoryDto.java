package nickzim.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private String nameUTF8;

    private String nameCP1251;

    private Integer count;

    public CategoryDto(String nameUTF8, String nameCP1251, Integer count) {
        this.nameUTF8 = nameUTF8;
        this.nameCP1251 = nameCP1251;
        this.count = count;
    }


}
