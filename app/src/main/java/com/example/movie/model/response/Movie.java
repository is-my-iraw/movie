package com.example.movie.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_name")
    @Expose
    private String originName;
    @SerializedName("membership_id")
    @Expose
    private String membershipId;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("episode_total")
    @Expose
    private String episodeTotal;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("showtimes")
    @Expose
    private String showtimes;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("trailer_url")
    @Expose
    private String trailerUrl;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("actors")
    @Expose
    private List<Actor> actors = null;

    @SerializedName("like_share")
    @Expose
    private String like_share;

    @SerializedName("Rating")
    @Expose
    private String rating;

    public Movie(Integer id, String name, String originName, String membershipId, String year, String content,
                 String type, String status, String slug, String episodeTotal, String lang,
                 String showtimes, String thumbUrl, String trailerUrl,
                 List<Category> categories, List<Director> directors, List<Actor> actors, String rating, String like_share) {
        this.id = id;
        this.name = name;
        this.originName = originName;
        this.membershipId = membershipId;
        this.year = year;
        this.content = content;
        this.type = type;
        this.status = status;
        this.slug = slug;
        this.episodeTotal = episodeTotal;
        this.lang = lang;
        this.showtimes = showtimes;
        this.thumbUrl = thumbUrl;
        this.trailerUrl = trailerUrl;
        this.categories = categories;
        this.directors = directors;
        this.actors = actors;
        this.rating = rating;
        this.like_share = like_share;
    }

    public Movie() {

    }

    public String getLike_share() {
        return like_share;
    }

    public void setLike_share(String like_share) {
        this.like_share = like_share;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getEpisodeTotal() {
        return episodeTotal;
    }

    public void setEpisodeTotal(String episodeTotal) {
        this.episodeTotal = episodeTotal;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(String showtimes) {
        this.showtimes = showtimes;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originName='" + originName + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", year='" + year + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", slug='" + slug + '\'' +
                ", episodeTotal='" + episodeTotal + '\'' +
                ", lang='" + lang + '\'' +
                ", showtimes='" + showtimes + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", categories=" + categories +
                ", directors=" + directors +
                ", actors=" + actors +
                ", rating=" + rating +
                '}';
    }

}

