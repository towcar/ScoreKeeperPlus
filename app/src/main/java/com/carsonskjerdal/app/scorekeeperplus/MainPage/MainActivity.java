package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Button;
import android.widget.EditText;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.GamePage.GameActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;
import com.carsonskjerdal.app.scorekeeperplus.SettingsPage.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    NavigationView navigationView;
    NewPlayerAdapter myAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView myRecycler = findViewById(R.id.recyclerPlayers);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        myRecycler.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myRecycler.getContext(), llm.getOrientation());
        myRecycler.addItemDecoration(dividerItemDecoration);
        NewPlayers player = new NewPlayers("", 0);
        List<NewPlayers> list = new ArrayList<>();
        list.add(player);

        myAdapter = new NewPlayerAdapter(list);
        myRecycler.scrollToPosition(myAdapter.getSize() - 1);

        myRecycler.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Button startButton = findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                ArrayList<String> listSend = new ArrayList<>();
                List<NewPlayers> updateList = myAdapter.getList();
                Log.e("main", "recieving " + updateList.get(0).getName());

                //child count is length so minus one we can get all the items we need
                for (int x = 0; x < myRecycler.getAdapter().getItemCount() - 1; x++) {
                    /*Log.e("main", "current count is " + x);
                    //get the view to get the edit text
                    Log.e("main", "child count is " + myRecycler.getChildCount());

                    View v = myRecycler.getLayoutManager().findViewByPosition(x);
                    Log.e("main", "current count is " + v);
                    //pull out name from view
                    EditText myText = v.findViewById(R.id.name);
                    String title = myText.getText().toString();*/
                    //attempt to grab the title regardless of recycler view's child view showing
                    NewPlayers player = updateList.get(x);
                    String title = player.getName();
                    listSend.add(title);
                    Log.e("main", "name is " + title);

                }

                Log.e("list send", "list: " + listSend);
                intent.putStringArrayListExtra("playersList", listSend);

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
