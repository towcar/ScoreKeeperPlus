package com.carsonskjerdal.app.scorekeeperplus.GamePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carsonskjerdal.app.scorekeeperplus.R;

import java.util.List;

/**
 * Created by Carson on 2018-02-01.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder>  {

    private List<Players> playerList;

    public PlayerAdapter(List<Players> list) {
        playerList = list;

    }


    /* ViewHolder for each item */
    public class PlayerHolder extends RecyclerView.ViewHolder  {


        TextView playerName;


        PlayerHolder(View itemView) {
            super(itemView);

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

    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }


}

