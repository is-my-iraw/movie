package com.example.movie.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.adapter.FavouriteAdapter;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Movie;
import com.example.movie.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity implements FavouriteAdapter.MovieInterfaceFavourite{


    FavouriteAdapter favouriteAdapter;

    private List<Movie> movieList = new ArrayList<>();

    RecyclerView fav_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        set_favourite();

    }

    public void set_favourite(){
        fav_list = findViewById(R.id.fav_list);
        ApiManager.getService().apiHome().enqueue(new Callback<BaseResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Movie>>> call, Response<BaseResponse<List<Movie>>> response) {
                movieList = response.body().getData();
                favouriteAdapter.setMovieList(movieList);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Movie>>> call, Throwable t) {

            }
        });

        favouriteAdapter = new FavouriteAdapter(movieList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        fav_list.setLayoutManager(gridLayoutManager);
        fav_list.setAdapter(favouriteAdapter);
        fav_list.setHasFixedSize(true);
    }

    @Override
    public void onClickMovieFilter(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE",movie);
        startActivity(intent);
    }
}

