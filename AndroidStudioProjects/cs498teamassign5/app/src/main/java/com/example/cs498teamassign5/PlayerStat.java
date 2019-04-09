package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerStat extends Activity implements View.OnClickListener {
    private ArrayList<GameInfo> allGames;
    private static final int MAIN_ACTIVITY_REQUEST = 1;
//    private int myPlayerScore = 0;
//    private int myPlayerRebound = 0;
//    private int myPlayerFoul = 0;
//    private int myPlayerTurnover = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stat);

        allGames = new ArrayList<>();

    }

    public void onClick(View v){
        if(v.getId() == R.id.NewGameButton) {
            Intent intent = new Intent(this, MainActivity.class);
            GameInfo gameInfo = new GameInfo();
            intent.putExtra("gameInfo", gameInfo);
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        }
    }

    public void updatePlayerStats(){
        /*update these
        myPlayerScore = 0;
        TextView playerPoints = (TextView) findViewById(R.id.PlayerPoints);
        playerPoints.setText(Integer.toString(myPlayerScore));

        myPlayerRebound = 0;
        TextView playerRebs = (TextView) findViewById(R.id.PlayerRebounds);
        playerRebs.setText(Integer.toString(myPlayerRebound));

        myPlayerFoul = 0;
        TextView playerFouls = (TextView) findViewById(R.id.PlayerFouls);
        playerRebs.setText(Integer.toString(myPlayerFoul));

        myPlayerTurnover = 0;
        TextView playerTurnovers = (TextView) findViewById(R.id.PlayerTurnovers);
        playerRebs.setText(Integer.toString(myPlayerTurnover));
        */


        //myTeamAdapter.notifyDataSetChanged();
        //opposingTeamAdapter.notifyDataSetChanged();
    }
}