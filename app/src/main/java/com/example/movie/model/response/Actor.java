
package com.example.movie.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Actor implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nick_name")
    @Expose
    private String nickName;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
