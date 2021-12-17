package com.example.foodapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodDescriptionFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FOOD_NAME = "foodName";
    private static final String ARG_IMAGE_URL = "imageUrl";
    private static final String ARG_TIME_TO_MAKE = "timeToMake";
    private static final String ARG_SPOONACULAR_SCORE = "spoonacularScore";
    private static final String ARG_HEALTH_SCORE = "healthScore";
    private static final String ARG_SUMMARY = "summary";
    private static final String ARG_SOURCE_URL = "sourceUrl";

    // TODO: Rename and change types of parameters
    private String mFoodName;
    private String mImageUrl;
    private int mTimeToMake;
    private int mSpoonacularScore;
    private int mHealthScore;
    private String mSummary;
    private String mSourceUrl;


    public FoodDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param foodName the name of the food from the clicked item on the recycler view page
     * @param imageUrl is the image URL for the food.
     * @param timeToMake is the time to make the dish in minutes.
     * @param spoonacularScore is the score of the dish on the spoonacularAPI.
     * @param healthScore is the health score of the dish.
     * @param summary is a quick summary of the dish.
     * @param sourceUrl is a URL for the entire recipe from the source page.
     * @return A new instance of fragment FoodDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDescriptionFragment newInstance(String foodName, String imageUrl, int timeToMake, int spoonacularScore, int healthScore, String summary, String sourceUrl) {
        FoodDescriptionFragment fragment = new FoodDescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOOD_NAME, foodName);
        args.putString(ARG_IMAGE_URL, imageUrl);
        args.putInt(ARG_TIME_TO_MAKE, timeToMake);
        args.putInt(ARG_SPOONACULAR_SCORE, spoonacularScore);
        args.putInt(ARG_HEALTH_SCORE, healthScore);
        args.putString(ARG_SUMMARY, summary);
        args.putString(ARG_SOURCE_URL, sourceUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodName = getArguments().getString(ARG_FOOD_NAME);
            mImageUrl = getArguments().getString(ARG_IMAGE_URL);
            mTimeToMake = getArguments().getInt(ARG_TIME_TO_MAKE);
            mSpoonacularScore = getArguments().getInt(ARG_SPOONACULAR_SCORE);
            mHealthScore = getArguments().getInt(ARG_HEALTH_SCORE);
            mSummary = getArguments().getString(ARG_SUMMARY);
            mSourceUrl = getArguments().getString(ARG_SOURCE_URL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_description, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView mTextViewFoodName = view.findViewById(R.id.tv_food_name_details);
        TextView mTextViewTimeToMake = view.findViewById(R.id.tv_ready_in_minutes_text);
        TextView mTextViewSpoonacularScore = view.findViewById(R.id.tv_spoonacular_score_text);
        TextView mTextViewHealthScore = view.findViewById(R.id.tv_health_score_text);
        TextView mTextViewSummary = view.findViewById(R.id.tv_summary_text);
        ImageView mImageView = view.findViewById(R.id.title_image2);


        mTextViewFoodName.setText(mFoodName);
        mTextViewTimeToMake.setText(mTimeToMake + "minutes");
        mTextViewSpoonacularScore.setText(mSpoonacularScore + "/100");
        mTextViewHealthScore.setText(mHealthScore + " points");
        mTextViewSummary.setText(mSummary);
        Picasso.get().load(mImageUrl).fit().centerInside().into(mImageView);

        Button btnLinkToRecipe = view.findViewById(R.id.btn_link_to_recipe_text);
        btnLinkToRecipe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_link_to_recipe_text){
            Log.d("Link click", "worked");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mSourceUrl));
            startActivity(intent);
        }
    }
}