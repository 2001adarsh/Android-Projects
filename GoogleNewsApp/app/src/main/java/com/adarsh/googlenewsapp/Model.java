package com.adarsh.googlenewsapp;

public class Model {
    public String title;
    String text;
    String ImageUrl;
    String author;
    String content;
    String description;

    public Model(String text, String imageUrl, String author, String content, String description) {
        this.text = text;
        ImageUrl = imageUrl;
        this.author = author;
        this.content = content;
        this.description = description;
    }
}
