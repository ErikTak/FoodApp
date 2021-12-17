package com.example.foodapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RecyclerViewFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ArrayList<FoodListItem> mFoodList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private RecyclerView.Adapter adapter;

    private static final String TAG = "RecyclerViewFragment";


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_FOOD_NAME = "foodName";
    public static final String ARG_DIET_PLAN = "dietPlan";
    public static final String ARG_ALLERGY_TYPE = "intoleranceType";

    private String mFoodName;
    private String mDietPlan;
    private String mIntoleranceType;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param foodName the name of the food from user input.
     * @param dietPlan the diet plan selected on the spinner.
     * @param intoleranceType is the data from the checkboxes.
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance(String foodName, String dietPlan, String intoleranceType) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOOD_NAME, foodName);
        args.putString(ARG_DIET_PLAN, dietPlan);
        args.putString(ARG_ALLERGY_TYPE, intoleranceType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodName = getArguments().getString(ARG_FOOD_NAME);
            mDietPlan = getArguments().getString(ARG_DIET_PLAN);
            mIntoleranceType = getArguments().getString(ARG_ALLERGY_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parseJSON();
    }



    private void parseJSON() {
        Uri uri = Utils.buildUri("https://api.spoonacular.com/recipes/complexSearch?apiKey=5da441b5a0254a19a401525d92b6cd73", "query", mFoodName, "addRecipeInformation", "true","number", "3", "diet", mDietPlan, "intolerance", mIntoleranceType);
        Log.d("Api link", uri.toString());

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, uri.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("API response: ", response.toString());
                Log.d("intolerance type:", mFoodName);
                Log.d("intolerance type:", mDietPlan);


                try {
                    // fetch JSONArray named results
//                    Log.d("try block start:", response.toString());
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);

                        String foodName = result.getString("title");
                        String imageUrl = result.getString("image");
                        int timeToMake = result.getInt("readyInMinutes");
                        int spoonacularScore = result.getInt("spoonacularScore");
                        int healthScore = result.getInt("healthScore");
                        String summary = result.getString("summary");
                        String sourceUrl = result.getString("sourceUrl");

                        mFoodList.add(new FoodListItem(imageUrl, foodName, timeToMake, spoonacularScore, healthScore, summary, sourceUrl));


                        Log.d("ArrayList(i) content: " ,mFoodList.get(i).getmFoodName());
                        mRecyclerView = getView().findViewById(R.id.rv_foodlist);

                        // wire up RecyclerView with the adapter
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new FoodsAdapter(mFoodList, new FoodsAdapter.CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                FoodListItem foodListItem = mFoodList.get(position);
//                                Log.d("It's working", "it's working:" + position);


                                // send data about the item clicked in the recycler view to the next screen
                                Bundle args = new Bundle();
                                args.putString("foodName", foodListItem.getmFoodName());
                                args.putString("imageUrl", foodListItem.getmImageUrl());
                                args.putInt("timeToMake", foodListItem.getmTimeToMake());
                                args.putInt("spoonacularScore", foodListItem.getmSpoonacularScore());
                                args.putInt("healthScore", foodListItem.getmHealthScore());
                                args.putString("summary", foodListItem.getmSummary());
                                args.putString("sourceUrl", foodListItem.getmSourceUrl());

                                Navigation.findNavController(getView()).navigate(R.id.action_recyclerViewFragment_to_foodDescriptionFragment, args);
                            }
                        });
                        mRecyclerView.setAdapter(adapter);
                    }

/*                    mFoodsAdapter = new FoodsAdapter(mFoodList);
                    mRecyclerView.setAdapter(mFoodsAdapter);*/

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), getString(R.string.toast_error_downloading_food), Toast.LENGTH_LONG);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue = Volley.newRequestQueue(getContext());
        mRequestQueue.add(stringRequest);
    }
}
