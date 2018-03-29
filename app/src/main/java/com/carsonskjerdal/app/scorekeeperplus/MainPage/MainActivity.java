package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.GamePage.GameActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;
import com.carsonskjerdal.app.scorekeeperplus.SettingsPage.SettingsActivity;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity implements AddPlayerInterface {

    NavigationView navigationView;
    NewPlayerAdapter myAdapter;
    CustomRecycler customRecycler;
    int position = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        customRecycler = findViewById(R.id.recyclerPlayers);

        //custom LayoutManager for adjusting speed
        VariableScrollSpeedLinearLayoutManager llm = new VariableScrollSpeedLinearLayoutManager(this, 50);
        //llm.setStackFromEnd(true);
        // myRecycler.setLayoutManager(llm);
        customRecycler.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(customRecycler.getContext(), llm.getOrientation());
        customRecycler.addItemDecoration(dividerItemDecoration);
        NewPlayers player = new NewPlayers("", 0);
        List<NewPlayers> list = new ArrayList<>();
        list.add(player);

        //definetly not the solution

        myAdapter = new NewPlayerAdapter(list) {
            @Override
            public void listListener() {
                Log.e("Main Activity","Scroll To Position");
                position = myAdapter.getSize();
                //customRecycler.smoothScrollToPosition(position - 1);

                customRecycler.post(new Runnable() {
                    @Override
                    public void run() {
                        customRecycler.smoothScrollToPosition(position - 1);
                    }
                });

            }
        };


        //myAdapter.registerAdapterDataObserver(observer);

        //myRecycler.scrollToPosition(myAdapter.getSize() - 1);

        //myRecycler.setAdapter(myAdapter);
        customRecycler.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        customRecycler.scrollToPosition(myAdapter.getItemCount());


        Button startButton = findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                ArrayList<String> listSend = new ArrayList<>();
                List<NewPlayers> updateList = myAdapter.getList();
                Log.e("main", "recieving " + updateList.get(0).getName());

                //child count is length so minus one we can get all the items we need
                for (int x = 0; x < customRecycler.getAdapter().getItemCount() - 1; x++) {
                    NewPlayers player = updateList.get(x);
                    String title = player.getName();
                    //check to ensure title has a character
                    if (!title.equals("")){
                        listSend.add(title);
                        Log.e("main", "name is " + title);
                    }
                }

                Log.e("list send", "list: " + listSend);
                //add list into intent
                intent.putStringArrayListExtra("playersList", listSend);

                //launch activity with the intent
                startActivity(intent);
                finish();
            }
        });

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addPlayer() {

    }

    @Override
    public void deletePlayer() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void editPlayer(String name, int position) {

    }

    @Override
    public void listListener() {

    }



   /* @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.





        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.

    }*/


}
