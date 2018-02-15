package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.util.Log;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class NewPlayers {

        private String name;

        public NewPlayers(String name){

            this.name = name;

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            //Log.e("getName","Name is: " + name);
            return name;
        }

    }

