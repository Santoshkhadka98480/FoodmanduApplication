package com.example.foodmandu.adaptter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmandu.R;
import com.example.foodmandu.model.Flavours;

import java.util.List;

public class FlavourAdapter extends RecyclerView.Adapter<FlavourAdapter.FlavourViewHolder>{
    Context context;
    List<Flavours> flavoursList;

    public FlavourAdapter(Context context, List<Flavours> flavoursList) {
        this.context = context;
        this.flavoursList = flavoursList;
    }

    @NonNull
    @Override
    public FlavourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flavours,parent,false);
        return new FlavourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavourViewHolder holder, int position) {
        Flavours flavours=flavoursList.get(position);
        holder.imageFlavour.setImageResource(flavours.getImg());
    }

    @Override
    public int getItemCount() {
        return flavoursList.size();
    }

    public class FlavourViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFlavour;
        public FlavourViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFlavour=itemView.findViewById(R.id.imageFlavour);
        }
    }
}
