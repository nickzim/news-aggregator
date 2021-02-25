package com.nickzim.newsaggregator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {

    private String title;
    private String link;
    private String pubDate;
    private String description;
    private String category;

}
