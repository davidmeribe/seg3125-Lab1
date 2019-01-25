package com.lab1.mortgagecalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_summary:
                    mTextMessage.setText(R.string.menu_summary);
                    return true;
                case R.id.navigation_details:
                    mTextMessage.setText(R.string.menu_details);
                    return true;
                //add more details option here
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.menu_settings);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner frequency = (Spinner) findViewById(R.id.frequency);
        Spinner currency = (Spinner) findViewById(R.id.currency);

        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        currency.setAdapter(currencyAdapter);


        ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(this, R.array.frequency, android.R.layout.simple_spinner_item);
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        currency.setAdapter(frequencyAdapter);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
