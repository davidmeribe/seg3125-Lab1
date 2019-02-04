package com.lab1.mortgagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        // Inflate the xml file for the fragment

        return inflater.inflate(R.layout.fragment_details, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set values for view here
        Bundle extras = getActivity().getIntent().getExtras();

        String principalString = extras.getString("principal");
        String interestString = extras.getString("interest");
        String periodString = extras.getString("period");

        int principalInt = Integer.valueOf(principalString);
        float interestInt = Float.parseFloat(interestString);
        int periodInt = Integer.valueOf(periodString.charAt(0));

        int str = Character.getNumericValue(periodString.charAt(0));

        TextView result = (TextView) getView().findViewById(R.id.textView12);
        TextView amountText = (TextView) getView().findViewById(R.id.textView7);
        TextView interestText = (TextView) getView().findViewById(R.id.textView13);
        TextView periodText = (TextView) getView().findViewById(R.id.textView15);

        result.setText(periodString.charAt(0)+"");
        amountText.setText("$"+principalInt);
        interestText.setText(interestInt+"%");
        periodText.setText(periodString);
    }

    // Activity is calling this to update view on Fragment
    public void updateView(int position){

    }

}
