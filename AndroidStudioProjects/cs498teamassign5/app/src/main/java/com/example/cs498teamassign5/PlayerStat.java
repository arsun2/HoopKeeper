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
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private GameInfo gameInfo;
    private int myPlayerScore = 0;
    private int myPlayerRebound = 0;
    private int myPlayerFoul = 0;
    private int myPlayerTurnover = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stat);

        Intent intent = getIntent();
        gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        updatePlayerStats();
    }

    public void onClick(View v) {

    }

    public void updatePlayerStats(){
        myPlayerScore = gameInfo.myPlayerScore();
        System.out.printf("myPlayerScore: %d\n", myPlayerScore);
        TextView playerPoints = (TextView) findViewById(R.id.PlayerPoints);
        playerPoints.setText(Integer.toString(myPlayerScore));

        myPlayerRebound = gameInfo.myPlayerRebound();
        TextView playerRebs = (TextView) findViewById(R.id.PlayerRebounds);
        playerRebs.setText(Integer.toString(myPlayerRebound));

        myPlayerFoul = gameInfo.myPlayerFoul();
        TextView playerFouls = (TextView) findViewById(R.id.PlayerFouls);
        playerFouls.setText(Integer.toString(myPlayerFoul));

        myPlayerTurnover = gameInfo.myPlayerTurnover();
        TextView playerTurnovers = (TextView) findViewById(R.id.PlayerTurnovers);
        playerTurnovers.setText(Integer.toString(myPlayerTurnover));
    }
}