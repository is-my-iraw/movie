package com.example.movie.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.activity.CategoryFilterActivity;
import com.example.movie.adapter.CategoryAdapter;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.network.ApiManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment implements CategoryAdapter.CategoryInterface {

    View view;


    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private List<Category> categoryList = new ArrayList<>();
    private List<String> categoryString = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_category, container, false);
        getCategory();
        init();
        return view;
    }


    public void getCategory(){
        ApiManager.getService().apiCategory().enqueue(new Callback<BaseResponse<List<Category>>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse<List<Category>>> call, Response<BaseResponse<List<Category>>> response) {
                Log.e("TAG", "onResponse: hello" + response.body());
                if (response.isSuccessful()){
                    if (response.body() != null){
                        List<Category> categories = response.body().getData();
                        categoryList = categories.stream().filter(Category::getShowHome).collect(Collectors.toList());
                        categoryAdapter.setData(categoryList);
                    }
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<List<Category>>> call, Throwable t) {
                Log.e("TAG", "onFailure: hello "+ t.getMessage());
            }
        });
    }
    private void init() {
        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(), 2);

        categoryAdapter = new CategoryAdapter(this.getActivity(), categoryList,this);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onClickCategory(Category category) {
        Intent detailCategory = new Intent(getContext(), CategoryFilterActivity.class);
        detailCategory.putExtra("MOVIE", category);
        startActivity(detailCategory);
    }
}