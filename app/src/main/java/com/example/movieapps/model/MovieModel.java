package com.example.movieapps.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;

    public  MovieModel() {
        
    }

    public MovieModel(int id, String original_title, String overview, String release_date, String poster_path) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
