package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.carsonskjerdal.app.scorekeeperplus.MainPage.AddPlayerInterface;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class MyTextWatcher implements TextWatcher {
    private EditText editText;
    private AddPlayerInterface listener;
    private int size;


    public MyTextWatcher(EditText editText, AddPlayerInterface pInterface, int listSize) {
        this.editText = editText;
        this.listener = pInterface;
        this.size = listSize;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Log.e("watcher", "sizebeforetext = " + size);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Log.e("watcher", "sizeontext = " + size);
    }

    @Override
    public void afterTextChanged(Editable s) {

        String name = s.toString();
        int position = (int) editText.getTag();
        size = listener.getSize();
        //int size = (int) editText.getTag(R.string.listSize);
        //size is declared above
        if (name.length() == 1) {
            if (position + 1 == size) {
                //add an item to the adapter list
                size += 1;
                listener.addPlayer();
            }
        } else if (name.length() == 0) {
            //check if the current position is proven to be one away from the last spot
            if (position + 2 == size) {
                //if last item is null then delete it
                listener.deletePlayer();
                size -= 1;
            }

            //scenario of deleting something even lower on the list
            if (position < size) {

            }


        }

    }
}