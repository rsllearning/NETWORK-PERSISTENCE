package com.example.networkapp.model;

public class Movie {
    String poster;
    String title;
    String category;

    public Movie(String poster,String title, String category) {
        this.poster=poster;
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
