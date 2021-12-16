package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


// delete this one if it's no longer random
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.RecyclerViewHolder> {

    private ArrayList<FoodListItem> mFoodList;

    public FoodsAdapter(ArrayList<FoodListItem> foodList){
        super();
        this.mFoodList = foodList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,
                        parent,
                        false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        FoodListItem foodListItem = mFoodList.get(position);


        holder.mTextViewFoodName.setText(foodListItem.getmFoodName());
        holder.mTextViewTimeToMake.setText("Preparation time: " + foodListItem.getmTimeToMake());
        Picasso.get().load(foodListItem.getmImageUrl()).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        if(mFoodList != null)
        return this.mFoodList.size();
        else return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public ImageView mImageView;
        public TextView mTextViewFoodName;
        public TextView mTextViewTimeToMake;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            mImageView = view.findViewById(R.id.iv_title_image);
            mTextViewFoodName = view.findViewById(R.id.tv_food_name);
            mTextViewTimeToMake = view.findViewById(R.id.tv_food_time_to_make);
        }
    }
}
