package com.example.carfirebase;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList <CarInfo> car = new ArrayList<>();
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.add_item);
            imageView= itemView.findViewById(R.id.image);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(parent.getContext())).inflate(R.layout.add_items,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.textView.setText("NameCar " + car.get(position).getName() +
              "\nModelCar " + car.get(position).getModel());
        try {
            Glide.with(holder.itemView.getContext()).load(car.get(position).getUri()).into(holder.imageView);
        }catch (Exception e)
        {
            System.out.println("erroe = " + e.getMessage());
        }
        System.out.println("Uri("+position+")  is " + car.get(position).getUri());
    }

    @Override
    public int getItemCount() {
        return car.size();
    }

    public void setListInfo(ArrayList<CarInfo> carInfos )
    {
        this.car = carInfos;
    }
}
