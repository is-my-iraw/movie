package com.example.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.adapter.CategoryFilterAdapter;
import com.example.movie.fragment.CategoryFragment;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;
import com.example.movie.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFilterActivity extends AppCompatActivity implements CategoryFilterAdapter.MovieInterfaceFilter {


    TextView TvNameCategory;
    RecyclerView TvCategoryFilter;

    CategoryFilterAdapter categoryFilterAdapter;

    List<Movie> categoryList = new ArrayList<>();


    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_filter);
        TvNameCategory = findViewById(R.id.TvNameCategory);
        TvCategoryFilter = findViewById(R.id.TvCategoryFilter);
        back = findViewById(R.id.BtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setVisibility(View.GONE);
                Fragment fragment = new CategoryFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment).commit();
            }
        });
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("MOVIE");
        TvNameCategory.setText(category.getName());
        CallApiFilter(category.getId());
    }

    private void init(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        categoryFilterAdapter = new CategoryFilterAdapter(this ,categoryList, this);

        TvCategoryFilter.setLayoutManager(gridLayoutManager);
        TvCategoryFilter.setAdapter(categoryFilterAdapter);
        TvCategoryFilter.setHasFixedSize(true);
    }

    private void CallApiFilter(int id){
        ApiManager.getService().getMovieByCategory(id).enqueue(new Callback<BaseResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Movie>>> call, Response<BaseResponse<List<Movie>>> response) {
                if (response.body() != null && response.isSuccessful()){
                    categoryList = response.body().getData();
                    Log.e("TAG", "onResponse: size " + categoryList.size());
                    categoryFilterAdapter.reloadDataMovie(categoryList);
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<List<Movie>>> call, Throwable t) {
                Log.e("TAG", "onResponse: hello" + t.getMessage());
            }
        });
    }
    @Override
    public void onClickMovieFilter(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE",movie);
        startActivity(intent);
    }
}