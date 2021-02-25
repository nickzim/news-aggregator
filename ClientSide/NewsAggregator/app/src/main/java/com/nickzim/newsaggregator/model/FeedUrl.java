package com.nickzim.newsaggregator.model;

import lombok.Data;

@Data
public class FeedUrl {

    private String url;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
