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
    private final Context context;

    public FoodsAdapter(Context context, ArrayList<FoodListItem> foodList){
        super();
        this.context = context;
        this.mFoodList = foodList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context)
                .inflate(R.layout.list_item,
                        parent,
                        false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        FoodListItem currentItem = this.mFoodList.get(i);

        String imageUrl = currentItem.getmImageUrl();
        String foodName = mFoodList.get(i).getmFoodName();
        int timeToMake = currentItem.getmTimeToMake();

        holder.mTextViewFoodName.setText(foodName);
        holder.mTextViewTimeToMake.setText("Preparation time: " + timeToMake);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return this.mFoodList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewFoodName;
        public TextView mTextViewTimeToMake;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_title_image);
            mTextViewFoodName = itemView.findViewById(R.id.tv_food_name);
            mTextViewTimeToMake = itemView.findViewById(R.id.tv_food_time_to_make);
        }
    }
}
