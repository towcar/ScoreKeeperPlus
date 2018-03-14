package com.carsonskjerdal.app.scorekeeperplus.AboutPage;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;

public class AboutActivity extends BaseActivity {

    NavigationView navigationView;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //set the about text
        text = findViewById(R.id.textViewAbout);
        setTextDesc();

    }

    public void setTextDesc(){
        String string = "ScoreKeeper Plus is an easy to use score tracker application that allows users to get away from boring pen and paper."
                + System.lineSeparator() +  System.lineSeparator() +  "We hope you enjoy keeping track of your games/score in a more modern approach. For help in development check " +
                "us out on github or leave a review for improvements you want to see. We look forward to improving the quality of Score Keeper Plus."
                + System.lineSeparator() +  System.lineSeparator() +  "Designed and Built by Carson Skjerdal";
        text.setText(string);
    }
}
