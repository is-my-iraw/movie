package com.example.movie.adapter;

import static org.greenrobot.eventbus.EventBus.TAG;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.response.Movie;

import java.util.List;
import java.util.stream.Collectors;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    private List<Movie> mListMovie;
    private List<Movie> _mCacheListMovie;

    Activity activity;
    MovieInterface movieInterface;



    public  interface  MovieInterface {
        void onClickMovie(Movie movie);
    }

    public SearchAdapter(Activity activity, List<Movie> mListMovie, MovieInterface movieInterface) {
        this.mListMovie = mListMovie;
        this.activity = activity;
        this.movieInterface = movieInterface;
        this._mCacheListMovie = mListMovie;
    }

    public void setData(List<Movie> mListMovie){
        this.mListMovie = mListMovie;
        this._mCacheListMovie = mListMovie;
        notifyItemRangeChanged(0, mListMovie.size());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mListMovie.get(position);
        ViewHolder userViewHolder = (ViewHolder) holder;

        Log.d("TAG", "setData: " + movie.getName());
        Glide.with(activity).load(movie.getThumbUrl()).into(userViewHolder.imgView);
        holder.TvNameSearch.setText(movie.getName());
        userViewHolder.itemView.setOnClickListener(v -> movieInterface.onClickMovie(movie));
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.e(TAG, "publishResults: performFiltering");
                String strSearch =  charSequence.toString().toLowerCase();
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListMovie.stream().filter(movie -> movie.getName().toLowerCase().contains(strSearch));
                return filterResults;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Log.e(TAG, "publishResults: publishResults");
                String strSearch =  charSequence.toString().toLowerCase();
                List<Movie> temp = _mCacheListMovie;
                if (strSearch.isEmpty()) {
                    mListMovie = temp;
                }else {
                    mListMovie = temp.stream().filter(movie -> movie.getName().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());
                }
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
      return mListMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imgView;
        private TextView TvNameSearch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_user);
            TvNameSearch = itemView.findViewById(R.id.tvNameSearch);
        }
    }
}
