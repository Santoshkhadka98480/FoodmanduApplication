package com.example.foodmandu.adaptter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmandu.LoginActivity;
import com.example.foodmandu.R;
import com.example.foodmandu.model.Categories;

import java.util.List;

public class CategoriesViewPagerAdapter extends RecyclerView.Adapter<CategoriesViewPagerAdapter.CategoryHolder> {
    Context context;
    List<Categories> categoriesAdpater;

    public CategoriesViewPagerAdapter(Context context, List<Categories> categoriesAdpater) {
        this.context = context;
        this.categoriesAdpater = categoriesAdpater;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        Categories categories = categoriesAdpater.get(position);
        holder.imageView.setImageResource(categories.getImg());
    }

    @Override
    public int getItemCount() {
        return categoriesAdpater.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageCategories);


        }
    }
}
