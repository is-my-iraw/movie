package com.example.movie;

import android.app.Application;
import android.content.Context;

import com.example.movie.model.response.Movie;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static Application instance;
    public static List<Movie> favourites  = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getListFavourites();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    protected void getListFavourites(){
//        favourites

        // gọi thằng SharedPreferences lưu list Favourites  convert thành json dùng gson đẻ convert
//    và gán lại data favourites

    }

    public static void setListFavourites(Movie favourite){
        favourites.add(favourite);
//        favourites

//        SharedPreferences xoa đi,  và lưu lại cái mới


    }
}
