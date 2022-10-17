package com.example.movie.adapter;

import static org.greenrobot.eventbus.EventBus.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
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

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder> {


    private Activity activity;
    MovieInterfaceFilter movieInterfaceFilter;

    private List<Movie> movieList = new ArrayList<>();


    public interface MovieInterfaceFilter {
        void onClickMovieFilter(Movie Movie);
    }


    public CategoryFilterAdapter(Activity activity, List<Movie> movieList, MovieInterfaceFilter movieInterfaceFilter) {
        this.activity = activity;
        this.movieList = movieList;
        this.movieInterfaceFilter = movieInterfaceFilter;
    }


    public void reloadDataMovie(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_category_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Movie movie = movieList.get(position);
        Log.e(TAG, "onBindViewHolder:" + movie.getName() );
        Glide.with(activity).load(movie.getThumbUrl()).into(holder.ivCoverCategory);
        holder.tvNameCategory.setText(movie.getName());
        holder.itemView.setOnClickListener(v -> movieInterfaceFilter.onClickMovieFilter(movie));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameCategory;
        ImageView ivCoverCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCoverCategory = itemView.findViewById(R.id.ivCoverCategory);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}
