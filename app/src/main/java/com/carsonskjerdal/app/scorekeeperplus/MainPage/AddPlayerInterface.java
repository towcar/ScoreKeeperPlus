package com.carsonskjerdal.app.scorekeeperplus.MainPage;


import android.database.DataSetObserver;

/**
 * Created by Carson on 2018-01-30.
 * <p>
 * Feel free to use code just give credit please :)
 */

interface AddPlayerInterface {
    void addPlayer();

    void deletePlayer();

    int getSize();

    void editPlayer(String name, int position);

    void listListener();
}
