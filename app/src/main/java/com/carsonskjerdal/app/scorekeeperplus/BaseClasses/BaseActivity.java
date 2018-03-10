package com.carsonskjerdal.app.scorekeeperplus.BaseClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.carsonskjerdal.app.scorekeeperplus.AboutPage.AboutActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */



public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        //swap this to a switch statement

        if (id == R.id.nav_newgame) {
            // Start new game
            finish();

            Log.e("Base","new Game");
        } else if (id == R.id.nav_players) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
