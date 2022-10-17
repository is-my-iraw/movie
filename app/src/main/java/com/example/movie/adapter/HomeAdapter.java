package com.example.movie.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;
import com.example.movie.network.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeAdapter extends RecyclerView.Adapter {

    public interface  HomeAdapterInterface {
        void updateListCategory(int position);
    }
    private Activity activity;

    private List<Category> listCategory;
    private HomeAdapterInterface homeAdapterInterface;


   public HomeAdapter(Activity activity, List<Category> listCategory,HomeAdapterInterface homeAdapterInterface) {
        this.activity = activity;
        this.homeAdapterInterface = homeAdapterInterface;
        this.listCategory = listCategory;
    }

    public void reloadData(List<Category> listCategory) {
        this.listCategory = listCategory;
//        Toast.makeText(activity, "reload Data", Toast.LENGTH_SHORT).show();
        notifyItemChanged(listCategory.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        return new SectionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SectionHolder vh = (SectionHolder) holder;
        Category section = listCategory.get(position);
        vh.tvTitle.setText(section.getName());

//        if (homeData.getCategory().equals(section) && homeData.getMovies().size() == 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            MovieAdapter movieAdapter = new MovieAdapter(activity);
            vh.rvMovie.setLayoutManager(layoutManager);
            vh.rvMovie.setHasFixedSize(true);
            vh.rvMovie.setAdapter(movieAdapter);
            ApiManager.getService().getMovieByCategory(section.getId()).enqueue(new Callback<BaseResponse<List<Movie>>>() {
                @Override
                public void onResponse(Call<BaseResponse<List<Movie>>> call, Response<BaseResponse<List<Movie>>> response) {
                    List<Movie> _cacheMovie = response.body().getData();
                    if (_cacheMovie.size() > 0) {
                        movieAdapter.reloadData(_cacheMovie);
                    } else {
                        Log.e("TAG", "onResponse: size " + listCategory.size());
                        if (listCategory.get(position).getId().equals(section.getId())) {
                            listCategory.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, listCategory.size());
                            Log.e("TAG", "onResponse: size " + listCategory.size());
                        }
                    }
                }
                @Override
                public void onFailure(Call<BaseResponse<List<Movie>>> call, Throwable t) {
                    Log.e("TAG", "onResponse: hello " + t.getMessage());
                }
            });
        }
    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvMovie;

        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvMovie = itemView.findViewById(R.id.rvMovie);
        }
    }
}

