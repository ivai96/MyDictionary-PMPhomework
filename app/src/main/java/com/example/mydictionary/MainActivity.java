package com.example.mydictionary;


import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();


    }

    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();

        SearchFragment searchFragment = new SearchFragment();

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // In portrait
//            Fragment f = manager.findFragmentById(R.id.landscape);
//            if(f!=null)
//            {
//                manager.beginTransaction().remove(f).commit();
//            }
            getSupportFragmentManager().beginTransaction().add(R.id.container, searchFragment).commit();
        } else {
            // In landscape
//            Fragment f = manager.findFragmentById(R.id.portrait);
//            if(f!=null)
//            {
//                manager.beginTransaction().remove(f).commit();
//            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
        }
    }

}

