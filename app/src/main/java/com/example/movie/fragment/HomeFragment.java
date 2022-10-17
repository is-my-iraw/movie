package com.example.movie.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.activity.DetailActivity;
import com.example.movie.adapter.HomeAdapter;
import com.example.movie.events.MessageEvent;
import com.example.movie.model.response.BaseResponse;
import com.example.movie.model.response.Category;
import com.example.movie.model.response.Movie;
import com.example.movie.network.ApiManager;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeAdapter.HomeAdapterInterface {


    View mView = null;

    List<Category> categories = new ArrayList<>();

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5};
    HomeAdapter homeAdapter;
    RecyclerView rvHome;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_home, container, false);
        initView();
        initBanner();
        initHome();
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        CallApiHome();

    }

    private void CallApiHome(){
        ApiManager.getService().apiCategory().enqueue(new Callback<BaseResponse<List<Category>>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse<List<Category>>> call, Response<BaseResponse<List<Category>>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<Category> filterCategoryShowHome =  response.body().getData();
                    categories = filterCategoryShowHome.stream().filter(Category::getShowHome).collect(Collectors.toList());
                    homeAdapter.reloadData(categories);
                    visibleRecycleView();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<List<Category>>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+ t.getMessage());
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initBanner() {
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int i, ImageView imageView) {
                imageView.setImageResource(sampleImages[i]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int i) {
//                Movie model = new Movie(+i, "https://cdnmedia.thethaovanhoa.vn/Upload/BLtvcXjb72tSqs1jiHr8g/files/2019/09/ipman4-2.jpg", "An over-the-hill hitman faces off against a younger clone of himself.");
//                goToDetail(model);
            }
        });
    }
    private void initHome() {
        //B1: Datasource
        //B2: Layout Manager
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        //B3: Adapter
        homeAdapter = new HomeAdapter(this.getActivity(), categories,this);

        hideRecycleView();

        rvHome.setLayoutManager(layoutManager);
        rvHome.setHasFixedSize(true);
        rvHome.setAdapter(homeAdapter);
    }

    private void initView() {
        carouselView = (CarouselView) mView.findViewById(R.id.carouselView);
        progressBar = mView.findViewById(R.id.progressBar);
        //B4: RecyclerView
        rvHome =  mView.findViewById(R.id.rvHome);
    }

    private void visibleRecycleView() {
        progressBar.setVisibility(View.GONE);
        rvHome.setVisibility(View.VISIBLE);
    }

    private void hideRecycleView() {
        progressBar.setVisibility(View.VISIBLE);
        rvHome.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MovieEvent event){
        Log.d("TAG", "onMessageEvent: "+event.getMovie().getName());
        goToDetail(event.getMovie());
    }

    private void goToDetail(Movie movie){
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
    }

    @Override
    public void updateListCategory(int position) {
//        categories.remove(position);
    }
}