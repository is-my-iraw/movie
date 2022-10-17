package com.example.movie.events;

import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;

public class MessageEvent {
    public static class MovieEvent {
        private Movie movie;

        public MovieEvent(Movie movie) {
            this.movie = movie;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }
    }

    public static class CategoryEvent {
        private Category category;

        public CategoryEvent(Category category) {
            this.category = category;
        }

        public Category getCategory() {
            return category;
        }

        public void setMovie(Category category) {
            this.category = category;
        }
    }
}
