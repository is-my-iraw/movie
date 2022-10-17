package com.example.movie.adapter;

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
import com.example.movie.events.MessageEvent;
import com.example.movie.model.response.Movie;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<Movie> movieList = new ArrayList<>();


    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public  void reloadData(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieHolder movieHolder = (MovieHolder) holder;
        Movie model = movieList.get(position);
        Glide.with(activity).load(model.getThumbUrl()).into(movieHolder.ivCover);
        movieHolder.tvName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView ivCover;
        TextView tvName;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvName = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movie movie = movieList.get(getAdapterPosition());
                    Log.d("TAG", "onClick: "+movie.getName());
                    EventBus.getDefault().post(new MessageEvent.MovieEvent(movie));
                }
            });
        }
    }
}
