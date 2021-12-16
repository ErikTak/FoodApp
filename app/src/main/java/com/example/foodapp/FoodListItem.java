package com.example.foodapp;

public class FoodListItem {
    private String mImageUrl;
    private String mFoodName;
    private int mTimeToMake;

    public FoodListItem(String imageUrl, String foodName, int timeToMake) {
        mImageUrl = imageUrl;
        mFoodName = foodName;
        mTimeToMake = timeToMake;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setFoodName(String inFoodName){
        this.mFoodName = inFoodName;

    }

    public String getFoodName(){
        return mFoodName;
    }

    public int getTimeToMake(){
        return mTimeToMake;
    }
}
