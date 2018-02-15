package com.carsonskjerdal.app.scorekeeperplus.MainPage;

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

public class NewPlayerAdapter extends RecyclerView.Adapter<NewPlayerAdapter.PlayerHolder> implements AddPlayerInterface {

    private List<NewPlayers> playerList;
    private AddPlayerInterface pInterface = this;

    public NewPlayerAdapter(List<NewPlayers> list) {
        playerList = list;

    }

    @Override
    public void addPlayer() {
        NewPlayers players = new NewPlayers("");
        //playerList.add(players);
        playerList.add(players);
        notifyItemInserted(playerList.size());
       // Log.e("Adapter", "size = " + playerList.size());
    }

    @Override
    public void deletePlayer(){
        playerList.remove(playerList.size() - 1);
        notifyItemChanged(playerList.size());
        //Log.e("Adapter", "size = " + playerList.size());
    }

    @Override
    public int getSize() {
        return playerList.size();
    }

    /* ViewHolder for each item */
    public class PlayerHolder extends RecyclerView.ViewHolder  {


        EditText playerName;


        PlayerHolder(View itemView) {
            super(itemView);
           // Log.e("Holder","setview");
            playerName = itemView.findViewById(R.id.name);
            int listSize = playerList.size();
            //Log.e("Adapter", "size = " + playerList.size());
            MyTextWatcher textWatcher = new MyTextWatcher(playerName, pInterface, listSize);
            playerName.addTextChangedListener(textWatcher);

        }
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_player_item_layout, parent, false);



        return new PlayerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerHolder holder, int position) {
        //Players playerItem = playerList.get(position);

        //Sets Text
        //holder.playerName.setText(playerItem.getName());
        //holder.playerName.setTag(R.string.listSize, playerList.size());
        holder.playerName.setTag( position);
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }


   public List<NewPlayers> getList(){
        Log.e("getList","List: " + playerList.size() + " is the size. It cointains at position 0: " + playerList.get(0).getName() );
        return playerList;
    }


}
