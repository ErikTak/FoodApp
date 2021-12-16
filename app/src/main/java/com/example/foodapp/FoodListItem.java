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

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public int getmTimeToMake() {
        return mTimeToMake;
    }

    public void setmTimeToMake(int mTimeToMake) {
        this.mTimeToMake = mTimeToMake;
    }
}