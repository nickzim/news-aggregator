package nickzim.Model.DTO;

public class CategoryDTO {

    private String nameUTF8;

    private String nameCP1251;

    private Integer count;

    public CategoryDTO(String nameUTF8, String nameCP1251, Integer count) {
        this.nameUTF8 = nameUTF8;
        this.nameCP1251 = nameCP1251;
        this.count = count;
    }


    public String getNameUTF8() {
        return nameUTF8;
    }

    public void setNameUTF8(String nameUTF8) {
        this.nameUTF8 = nameUTF8;
    }

    public String getNameCP1251() {
        return nameCP1251;
    }

    public void setNameCP1251(String nameCP1251) {
        this.nameCP1251 = nameCP1251;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
