package com.ming6464.minhngph25430_assignmnet.DTO;

public class ItemWeb {
    private String title,link;

    public ItemWeb(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public ItemWeb() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "title: " + this.title;
    }
}

