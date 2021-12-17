package com.example.foodapp;

public class FoodListItem {
//    private static String mFoodName;
    private String mImageUrl;
    private String mFoodName;
    private int mTimeToMake;
    private int mSpoonacularScore;
    private int mHealthScore;
    private String mSummary;
    private String mSourceUrl;


    public FoodListItem(String imageUrl, String foodName, int timeToMake, int spoonacularScore, int healthScore, String summary, String sourceUrl) {
        mImageUrl = imageUrl;
        mFoodName = foodName;
        mTimeToMake = timeToMake;
        mSpoonacularScore = spoonacularScore;
        mHealthScore = healthScore;
        mSummary = summary;
        mSourceUrl = sourceUrl;
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

    public int getmSpoonacularScore() {
        return mSpoonacularScore;
    }

    public void setmSpoonacularScore(int mSpoonacularScore) {
        this.mSpoonacularScore = mSpoonacularScore;
    }

    public int getmHealthScore() {
        return mHealthScore;
    }

    public void setmHealthScore(int mHealthScore) {
        this.mHealthScore = mHealthScore;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getmSourceUrl() {
        return mSourceUrl;
    }

    public void setmSourceUrl(String mSourceUrl) {
        this.mSourceUrl = mSourceUrl;
    }
}