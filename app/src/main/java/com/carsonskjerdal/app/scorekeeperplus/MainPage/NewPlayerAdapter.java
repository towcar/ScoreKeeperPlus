package com.carsonskjerdal.app.scorekeeperplus.MainPage;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.carsonskjerdal.app.scorekeeperplus.R;

import java.util.List;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */

public abstract class NewPlayerAdapter extends RecyclerView.Adapter<NewPlayerAdapter.PlayerHolder> implements AddPlayerInterface {

    private List<NewPlayers> playerList;
    private AddPlayerInterface pInterface = this;


    NewPlayerAdapter(List<NewPlayers> list) {
        playerList = list;

    }

    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(AddPlayerInterface listener) {
        this.pInterface = listener;
    }

    @Override
    public void addPlayer() {
        //add player with blank name and position of size minus one (starts at zero)
        NewPlayers players = new NewPlayers("", playerList.size() - 1);
        playerList.add(players);
        notifyItemInserted(playerList.size());
        //calls listener in main activity
        pInterface.listListener();
        Log.e("Edit Text", "Player Added at" + playerList.size());
    }

    @Override
    public void deletePlayer() {
        playerList.remove(playerList.size() - 1);
        notifyItemChanged(playerList.size());
        //calls listener in main activity
        pInterface.listListener();
        //Log.e("Adapter", "size = " + playerList.size());
    }

    @Override
    public int getSize() {
        return playerList.size();
    }

    @Override
    public void editPlayer(String name, int position) {
        //update player list based on position and passed in text
        NewPlayers players = playerList.get(position);
        players.setName(name);
        //set the item with the position and the player passed through
        playerList.set(position, players);
        //calls the custome recyclerview listener to update
        //listener.listListener();
    }




    /* ViewHolder for each item */
    public class PlayerHolder extends RecyclerView.ViewHolder {


        EditText playerName;


        PlayerHolder(View itemView) {
            super(itemView);
            // Log.e("Holder","setview");
            playerName = itemView.findViewById(R.id.name);
            int listSize = playerList.size();

            //Log.e("Adapter", "size = " + playerList.size());
            int position = getAdapterPosition();
            Log.e("Adapter", "id" + position);

            MyTextWatcher textWatcher = new MyTextWatcher(playerName, pInterface, listSize, position);
            playerName.addTextChangedListener(textWatcher);
            //need to maybe pass in a position or something to update list size

        }
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_player_item_layout, parent, false);


        /*int height = parent.getMeasuredHeight() / 4;
        int width = parent.getMeasuredWidth();

        itemView.setLayoutParams(new RecyclerView.LayoutParams(width, height));*/

        return new PlayerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        //holder.setIsRecyclable(false);
        NewPlayers playerItem = playerList.get(position);

        //Sets Text
        holder.playerName.setText(playerItem.getName());
        //holder.playerName.setTag(R.string.listSize, playerList.size());
        holder.playerName.setTag(position);
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }


    public List<NewPlayers> getList() {
        Log.e("Edit Text", "List: " + playerList.size() + " is the size. It cointains at position 0: " + playerList.get(0).getName());
        return playerList;
    }


}
