package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Carson on 3/28/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class CustomRecycler extends RecyclerView {
    private ListListener listener;

    public CustomRecycler(Context context) {
        super(context);
        this.listener = null;
    }

    public CustomRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(ListListener listener) {
        this.listener = listener;
    }


}
