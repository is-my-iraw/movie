package com.example.movie.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("isShowHome")
    @Expose
    private Boolean isShowHome;

    private String image;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isShowHome=" + isShowHome +
                ", image='" + image + '\'' +
                '}';
    }

    public Category() {
    }

    public Category(Integer id, String name, Boolean isShowHome, String image) {
        this.id = id;
        this.name = name;
        this.isShowHome = isShowHome;
        this.image = image;
    }

    public Boolean getShowHome() {
        return isShowHome;
    }

    public void setShowHome(Boolean showHome) {
        isShowHome = showHome;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
