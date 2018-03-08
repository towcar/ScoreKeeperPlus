package com.carsonskjerdal.app.scorekeeperplus.GamePage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carsonskjerdal.app.scorekeeperplus.BaseClasses.BaseActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;

import java.util.List;

/**
 * Created by Carson on 2018-02-01.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> implements View.OnClickListener {

    private List<Players> playerList;
    private Context mContext;

    PlayerAdapter(List<Players> list) {
        playerList = list;
    }

    @Override
    public void onClick(View view) {
    }


    /* ViewHolder for each item */
    class PlayerHolder extends RecyclerView.ViewHolder  {


        TextView playerName;
        TextView playerScore;


        PlayerHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            playerName = itemView.findViewById(R.id.playerName);
            playerScore = itemView.findViewById(R.id.playerScore);
        }

    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item_layout, parent, false);

        return new PlayerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerHolder holder, int position) {
        Players playerItem = playerList.get(position);


        //Sets Text

        holder.playerName.setText(playerItem.getName());

        //set Highlighted if  selected
        final int accentColor = getThemeAccentColor(mContext);
        if (playerItem.getSelectState()){
        holder.itemView.setBackgroundColor(accentColor);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        //set score
        holder.playerScore.setText(String.valueOf(playerItem.getScore()));

    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }

    private static int getThemeAccentColor(Context context) {
        int colorAttr;
        colorAttr = android.R.attr.colorAccent;
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

}

