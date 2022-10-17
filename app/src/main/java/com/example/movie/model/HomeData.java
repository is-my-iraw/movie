package com.example.movie.model;

import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;

import java.util.List;

public class HomeData {
    private Category category;
    private List<Movie> movies;

    public HomeData() {
    }

    public HomeData(Category category, List<Movie> movies) {
        this.category = category;
        this.movies = movies;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
