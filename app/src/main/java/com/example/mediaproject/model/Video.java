package com.example.mediaproject.model;

public class Video {

    private String title;
    private String source;
    private String thumnail;
    private String description;

    public Video(String title, String source, String thumnail, String description) {
        this.title = title;
        this.source = source;
        this.thumnail = thumnail;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
