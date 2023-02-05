package com.nickzim.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News {

    private String agency;

    private String title;

    private String link;

    private LocalDateTime pubDate;

    private String description;

    private String category;


}
