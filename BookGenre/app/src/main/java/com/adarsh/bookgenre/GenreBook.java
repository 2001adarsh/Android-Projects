package com.adarsh.bookgenre;

public class GenreBook {
    private int images;
    private String Genre, Desc;

    public GenreBook(int images, String genre, String desc) {
        this.images = images;
        Genre = genre;
        Desc = desc;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
