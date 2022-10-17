package com.example.movie.adapter;

import static org.greenrobot.eventbus.EventBus.TAG;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.response.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.UserViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    MovieInterfaceFavourite MovieInterfaceFavourite;
    Activity activity;

    public FavouriteAdapter(List<Movie> movieList, Activity activity) {
        this.movieList = movieList;
        this.activity = activity;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    public interface MovieInterfaceFavourite {
        void onClickMovieFilter(Movie movie);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Log.e(TAG, "onBindViewHolder:" + movie.getName() );
        Glide.with(activity).load(movie.getThumbUrl()).into(holder.img_favorite);
        holder.tv_name_favorite.setText(movie.getName());
        holder.itemView.setOnClickListener(v -> MovieInterfaceFavourite.onClickMovieFilter(movie));
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView img_favorite;
        TextView tv_name_favorite;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img_favorite = itemView.findViewById(R.id.ivCover_favorite);
            tv_name_favorite = itemView.findViewById(R.id.tvName_favorite);
        }
    }
}
