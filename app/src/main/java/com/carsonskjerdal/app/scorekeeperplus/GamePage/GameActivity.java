package com.carsonskjerdal.app.scorekeeperplus.GamePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.MainPage.MainActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;
import com.carsonskjerdal.app.scorekeeperplus.SettingsPage.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends BaseActivity
        implements Button.OnClickListener {

    public List<Players> list = new ArrayList<>();
    PlayerAdapter myAdapter;
    RecyclerView myRecycler;
    NavigationView navigationView;
    EditText passPoints;
    Button addButton;
    Button subtractButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addButton = findViewById(R.id.button1);
        addButton.setOnClickListener(this);
        subtractButton = findViewById(R.id.button2);
        subtractButton.setOnClickListener(this);



        Intent intent = getIntent();
        ArrayList<String> listSend = intent.getStringArrayListExtra("playersList");


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        myRecycler = findViewById(R.id.recyclerPlayers);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecycler.setLayoutManager(llm);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myRecycler.getContext(), llm.getOrientation());
        myRecycler.addItemDecoration(dividerItemDecoration);


        for (int i = 0; i < listSend.size(); i++) {
            Players player = new Players(listSend.get(i), 0, false);
            list.add(player);
        }

        myAdapter = new PlayerAdapter(list);


        myRecycler.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        passPoints = findViewById(R.id.editText);

        //set up seek bar listener
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //set text
                //passOverValue();
                passPoints.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final int accentColor = getThemeAccentColor(this);

        //extend so we can make this its own method?
        myRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, myRecycler, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                    Log.e("Game Activity"," Click detected");
                    if (view.isSelected()){
                        view.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        Players playerEdit = list.get(position);
                        playerEdit.setSelectState(false);
                    } else {
                        view.setBackgroundColor(accentColor);
                        view.setSelected(true);
                        Players playerEdit = list.get(position);
                        playerEdit.setSelectState(true);
                    }

                    //Loop and set the selected one? Maybe leave it so multiple are selected

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    //add functions here
                    }
                })
        );


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
        getMenuInflater().inflate(R.menu.game, menu);
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
            Intent intent = new Intent(GameActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void passOverValue() {
        //for unit testing
    }

    @Override
    public void onClick(View view) {

        //do operation according to which modifier button is selected
        switch (view.getId()) {
            case R.id.button1:
                //add button
                updateScore(true);
                break;

            case R.id.button2:
                //subtract button
                updateScore(false);
                break;


        }
    }

    //loops through list to find out which item is selected
    public void updateScore(Boolean AreWeAddding){
        int newScore = 0;
        // Code for button clicks
        for (int i = 0; i < list.size(); i++) {

            //find which items have isSelected;
            Players player = list.get(i);
            if (player.getSelectState()) {
                //add to the items
                int oldScore = player.getScore();
                //check to make sure value isn't 0
                if (passPoints.getText().toString().equals("")){
                    newScore = 0;
                } else {
                    newScore = Integer.parseInt(passPoints.getText().toString());
                }

                //adds or subtracts according to button clicked
                if(AreWeAddding){
                    player.setScore(oldScore + newScore);
                } else {
                    player.setScore(oldScore - newScore);
                }

            }

        }
        //update adapter list
        myAdapter.notifyDataSetChanged();
    }


    private static int getThemeAccentColor(Context context) {
        int colorAttr;
        colorAttr = android.R.attr.colorAccent;
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

}
//ToDo - insert game modes. (mini-golf, etc?)