package com.example.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.activity.DetailActivity;
import com.example.movie.adapter.HomeAdapter;
import com.example.movie.adapter.SearchAdapter;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;
import com.example.movie.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements SearchAdapter.MovieInterface {

    private View mView;
    RecyclerView rvHome;

    List<Category> categories = new ArrayList<>();

    HomeAdapter homeAdapter;

    List<Movie> movieList = new ArrayList<>();

    SearchView search_view;

//    ProgressBar searchprogressbar;


    RecyclerView tvSearch;
    SearchAdapter searchAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_search, container, false);
        initView();
        setOnClickSearch();
        Search();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getListAllMovie();
    }

    private void initView() {
//        searchprogressbar = mView.findViewById(R.id.searchprogressbar);
        tvSearch = mView.findViewById(R.id.tvSearch);
        //B4: RecyclerView
    }

    private void Search(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        tvSearch.setLayoutManager(linearLayoutManager);

//        hideRecycleView();

        searchAdapter = new SearchAdapter(this.getActivity(),movieList, this);
        tvSearch.setAdapter(searchAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        tvSearch.addItemDecoration(itemDecoration);
    }

    private void getListAllMovie() {
//        visibleRecycleView();
        ApiManager.getService().apiHome().enqueue(new Callback<BaseResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Movie>>> call, Response<BaseResponse<List<Movie>>> response) {
//                hideRecycleView();
                if (response.isSuccessful()){
                    if (response.body() != null){
//                        Toast.makeText(getContext(), "hello ae", Toast.LENGTH_SHORT).show();
                        List<Movie> movies = response.body().getData();
                        searchAdapter.setData(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Movie>>> call, Throwable t) {
//                hideRecycleView();
                // error in getting json
                Toast.makeText(
                        getContext(),
                        "Please check your internet connection or connect to another network.",
                        Toast.LENGTH_LONG)
                        .show();

            }
        });
    }



    private void getListMovieByName(String name) {
//        visibleRecycleView();
        ApiManager.getService().search(name).enqueue(new Callback<BaseResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Movie>>> call, Response<BaseResponse<List<Movie>>> response) {
//                hideRecycleView();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        List<Movie> movies = response.body().getData();
                        searchAdapter.setData(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Movie>>> call, Throwable t) {
//                hideRecycleView();
                // error in getting json
                Toast.makeText(
                        getContext(),
                        "Please check your internet connection or connect to another network.",
                        Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

//    private void visibleRecycleView() {
//        searchprogressbar.setVisibility(View.GONE);
//        tvSearch.setVisibility(View.VISIBLE);
//    }
//
//    private void hideRecycleView() {
//        searchprogressbar.setVisibility(View.VISIBLE);
//        tvSearch.setVisibility(View.GONE);
//    }

    private void setOnClickSearch(){
        search_view = mView.findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getListMovieByName(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClickMovie(Movie movie) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
    }

}