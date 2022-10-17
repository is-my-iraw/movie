package com.example.movie.model;

import com.example.movie.model.response.Movie;

import java.util.List;

public class Section {
    private String title;
    private List<Movie> movies;

    public Section() {
    }

    public Section(String title, List<Movie> movies) {
        this.title = title;
        this.movies = movies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
