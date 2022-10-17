package com.example.movie.adapter;

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
import com.example.movie.model.response.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.UserViewHolder>{
    Activity activity;
    private List<Category> mListCategory;
    CategoryInterface iCategoryInterface;

    public  interface  CategoryInterface {
        void onClickCategory(Category category);
    }

    public CategoryAdapter(Activity activity, List<Category> mListCategory, CategoryInterface iCategoryInterface) {
        this.activity = activity;
        this.mListCategory = mListCategory;
        this.iCategoryInterface = iCategoryInterface;
    }


    public void setData(List<Category> mListCategory) {
        this.mListCategory = mListCategory;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        UserViewHolder userViewHolder = (UserViewHolder) holder;

        Log.d("TAG", "setData: " + category.getName());
        Glide.with(activity).load(category.getImage()).into(userViewHolder.imgCategory);
        holder.tv_name.setText(category.getName());
        userViewHolder.itemView.setOnClickListener(v -> iCategoryInterface.onClickCategory(category));
    }

    @Override
    public int getItemCount() {
        return mListCategory.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
         ImageView imgCategory;
         TextView tv_name;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imageCategory);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
