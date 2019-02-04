package com.lab1.mortgagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        // Inflate the xml file for the fragment

        return inflater.inflate(R.layout.fragment_settings, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set values for view here
        List<String> spinnerCurrency = new ArrayList<String>();
        spinnerCurrency.add("Dollar");
        spinnerCurrency.add("Euro");
        spinnerCurrency.add("Pound");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerCurrency);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) getView().findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);

        List<String> spinnerFreq = new ArrayList<String>();
        spinnerFreq.add("Bi-weekly");
        spinnerFreq.add("Weekly");
        spinnerFreq.add("Monthly");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerFreq);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems1 = (Spinner) getView().findViewById(R.id.spinner3);
        sItems1.setAdapter(adapter1);
    }

    // Activity is calling this to update view on Fragment
    public void updateView(int position){

    }
}
