package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.util.Log;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class NewPlayers {

        private String name;
        private int position;

        public NewPlayers(String name, int position){

            this.name = name;
            this.position = position;

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            //Log.e("getName","Name is: " + name);
            return name;
        }

        public void setPosition(int position){this.position = position;}

        public int getPosition(){return position;}

    }

