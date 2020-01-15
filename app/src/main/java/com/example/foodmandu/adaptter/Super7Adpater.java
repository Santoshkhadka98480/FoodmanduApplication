package com.example.foodmandu.adaptter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmandu.R;
import com.example.foodmandu.model.Super7;

import java.util.List;


public class Super7Adpater extends RecyclerView.Adapter<Super7Adpater.SuperViewHoler> {
    Context context;
    List<Super7> listsuper;

    public Super7Adpater(Context context, List<Super7> listsuper) {
        this.context = context;
        this.listsuper = listsuper;
    }

    @NonNull
    @Override
    public SuperViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.super7,parent,false);
        return new SuperViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHoler holder, int position) {
        Super7 dataSuper=listsuper.get(position);
        holder.image.setImageResource(dataSuper.getImage());
    }

    @Override
    public int getItemCount() {
        return listsuper.size();
    }

    public class SuperViewHoler extends RecyclerView.ViewHolder
    {


        ImageView image;


        public SuperViewHoler(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageSuper7);

        }
    }
}
