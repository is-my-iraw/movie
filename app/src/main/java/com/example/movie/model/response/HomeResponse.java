package com.example.movie.model.response;

import java.util.List;

public class HomeResponse {
    private List<Movie> trending;
    private List<Movie> hot;
    private List<Movie> popular;
    private List<Movie> upcoming;

    public List<Movie> getTrending() {
        return trending;
    }

    public void setTrending(List<Movie> trending) {
        this.trending = trending;
    }

    public List<Movie> getHot() {
        return hot;
    }

    public void setHot(List<Movie> hot) {
        this.hot = hot;
    }

    public List<Movie> getPopular() {
        return popular;
    }

    public void setPopular(List<Movie> popular) {
        this.popular = popular;
    }

    public List<Movie> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<Movie> upcoming) {
        this.upcoming = upcoming;
    }
}
