package com.example.foodapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchSettingsFragment extends Fragment implements View.OnClickListener {


    public SearchSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchSettingsFragment newInstance() {
        SearchSettingsFragment fragment = new SearchSettingsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_settings, container, false);

        Button btn_find_meals = view.findViewById(R.id.btn_find_meals);
        btn_find_meals.setOnClickListener(this);

        Spinner spnDietType = view.findViewById(R.id.spn_diet_type);
        ArrayAdapter<CharSequence> spnAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spn_diet_choice_array, android.R.layout.simple_spinner_item);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDietType.setAdapter(spnAdapter);

        return view;
    }


    @Override
    public void onClick(View view) {

        // Get the food diet type
        Spinner spnDietType = getView().findViewById(R.id.spn_diet_type);
        String diettype = spnDietType.getSelectedItem().toString();
        // Get the allergy type
        

        if (view.getId() == R.id.btn_find_meals){
            // Get the name of the food entered by the user
            EditText etEnterFood = getView().findViewById(R.id.et_enter_food);
            String enterfood = etEnterFood.getText().toString();

            // Create a bundle for the arguments
            Bundle args = new Bundle();
            args.putString("foodName", enterfood);
            args.putString("dietPlan", diettype);

            Navigation.findNavController(view).navigate(R.id.action_searchSettingsFragment_to_recyclerViewFragment, args);
        }
    }
}