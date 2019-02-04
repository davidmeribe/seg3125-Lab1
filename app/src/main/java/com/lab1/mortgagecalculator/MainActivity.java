package com.lab1.mortgagecalculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnFragmentInteractionListener,
                                              SummaryFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;
    private FragmentTransaction ft;
    private Fragment currentFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(new WelcomeFragment());
                    return true;
                case R.id.navigation_summary:
                    //mTextMessage.setText(R.string.menu_summary);
                    switchFragment(new SummaryFragment());
                    return true;
                case R.id.navigation_details:
                    mTextMessage.setText(R.string.menu_details);
                    return true;
                case R.id.navigation_settings:
                    //mTextMessage.setText(R.string.menu_settings);
                    //SeetingActivity.
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new WelcomeFragment();
        ft.replace(R.id.mainContent,currentFragment);
        ft.commit();


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void switchFragment(Fragment fragment){
         currentFragment = fragment;
         ft = getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.mainContent, fragment);
         ft.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
