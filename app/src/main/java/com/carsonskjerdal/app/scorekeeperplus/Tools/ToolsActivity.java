package com.carsonskjerdal.app.scorekeeperplus.Tools;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;

import java.util.Random;

public class ToolsActivity extends BaseActivity {

    private ImageView bottle;
    private int lastAngle = -1;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View main = findViewById(R.id.root);
        bottle = findViewById(R.id.bottle);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinTheBottle();
            }
        });

        Toast.makeText(this, "Touch To Spin", Toast.LENGTH_SHORT).show();

    }


    private void spinTheBottle() {
        Random r = new Random();
        int angle = (r.nextInt(3600 - 360) + 360);
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;


        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animRotate.setDuration(2500);
        animRotate.setFillAfter(true);

        bottle.startAnimation(animRotate);

    }

    private void resetTheBottle() {
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;

        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animRotate.setDuration(2000);
        animRotate.setFillAfter(true);

        bottle.startAnimation(animRotate);
        Toast.makeText(this, "Resetting Bottle", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tools, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else
        if (id == R.id.action_respin) {
            resetTheBottle();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
