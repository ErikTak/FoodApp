package com.example.foodapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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


public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<FoodListItem> mFoodList = new ArrayList<>();
    private RequestQueue mRequestQueue;

    private static final String TAG = "RecyclerViewFragment";


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_FOOD_NAME = "foodName";
    public static final String ARG_DIET_PLAN = "dietPlan";

    private String mFoodName;
    private String mDietPlan;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param foodName the name of the food from user input.
     * @param dietPlan the diet plan selected on the spinner.
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance(String foodName, String dietPlan) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOOD_NAME, foodName);
        args.putString(ARG_DIET_PLAN, dietPlan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodName = getArguments().getString(ARG_FOOD_NAME);
            mDietPlan = getArguments().getString(ARG_DIET_PLAN);
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

        // create the adapter for the RecyclerView
        FoodsAdapter adapter = new FoodsAdapter(getContext(), mFoodList);

        // get the RecyclerView
        mRecyclerView = view.findViewById(R.id.rv_foodlist);

        // wire up RecyclerView with the adapter
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        mFoodList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(view.getContext());
        parseJSON();
    }

    private void parseJSON() {
        Uri uri = Utils.buildUri("https://api.spoonacular.com/recipes/complexSearch?apiKey=5da441b5a0254a19a401525d92b6cd73", "query", mFoodName, "addRecipeInformation", "true","number", "2");

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, uri.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("API response: ", response.toString());

                //mFoodList.add(new FoodListItem("https:\\/\\/spoonacular.com\\/recipeImages\\/654959-312x231.jpg", "Pasta With Tuna", 45));
                try {
                    // fetch JSONArray named results
                    Log.d("try block start:", response.toString());
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);

                        String foodName = result.getString("title");
                        String imageUrl = result.getString("image");
                        int timeToMake = result.getInt("readyInMinutes");


                        FoodListItem fli = new FoodListItem(imageUrl, foodName, timeToMake);
                        fli.setmFoodName(foodName);
                        fli.setmImageUrl(imageUrl);
                        fli.setmTimeToMake(timeToMake);
                        mFoodList.add(fli);
                        Log.d("arraylist content: " ,mFoodList.get(i).getmFoodName());
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

        mRequestQueue.add(stringRequest);
    }
}
