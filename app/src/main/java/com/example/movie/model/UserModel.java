package com.example.movie.model;

public class UserModel {
    public int resouceImage;
    public String name;

    public UserModel(int resouceImage, String name) {
        this.resouceImage = resouceImage;
        this.name = name;
    }

    public int getResouceImage() {
        return resouceImage;
    }

    public void setResouceImage(int resouceImage) {
        this.resouceImage = resouceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "resouceImage=" + resouceImage +
                ", name='" + name + '\'' +
                '}';
    }

    public UserModel() {
    }
}
