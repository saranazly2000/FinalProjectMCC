package com.example.project;

public class News {
    private String title;
    private String description;
    private String publishedAt;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public News(String title, String description, String publishedAt) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public News() {
    }
}
