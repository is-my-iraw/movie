package com.example.movie.network;

import com.example.movie.model.requets.LoginRequest;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;
import com.example.movie.model.response.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //    String SERVICE = "http://10.0.2.2:8080";
    String SERVER =  "http://10.0.2.2:8081";

    @POST("/api/login")
    Call<BaseResponse<Token>> apiLogin(@Body LoginRequest loginRequest);

    @GET("/api/movies/all")
    Call<BaseResponse<List<Movie>>> apiHome();

    @GET("/api/category/all")
    Call<BaseResponse<List<Category>>> apiCategory();

    @GET("/api/movies/category/{id}")
    Call<BaseResponse<List<Movie>>> getMovieByCategory(@Path("id") int id);

    @GET("/api/movies/search")
    Call<BaseResponse<List<Movie>>> search(@Query("query") String name);

    @GET("/api/movies/user-save")
    Call<BaseResponse<List<Movie>>> apiFavourite();

}
