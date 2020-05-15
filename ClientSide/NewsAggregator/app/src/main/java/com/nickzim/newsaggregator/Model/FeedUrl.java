package com.nickzim.newsaggregator.Model;

public class FeedUrl {

    FeedUrl(){
        this.url = null;
    }

    public FeedUrl(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;


}
