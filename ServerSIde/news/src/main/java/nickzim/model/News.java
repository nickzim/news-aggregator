package nickzim.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class News {

    private String agency;

    private String title;

    private String link;

    private LocalDateTime pubDate;

    private String description;

    private String category;


}
