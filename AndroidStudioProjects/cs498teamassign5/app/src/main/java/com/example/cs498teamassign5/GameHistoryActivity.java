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
import java.util.Arrays;
import java.util.List;

public class GameHistoryActivity extends Activity implements View.OnClickListener {
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private GameInfo gameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        Intent intent = getIntent();
        Bundle gameHistInfo = intent.getExtras();
        gameInfo = (GameInfo) gameHistInfo.getSerializable("gameInfo");

        updatePlayerStats();
    }

    public void onClick(View v) {

    }

    public void updatePlayerStats(){
        int myTeamScore = gameInfo.myTeamScore();
        TextView playerPoints = (TextView) findViewById(R.id.myTeamPoints);
        playerPoints.setText(Integer.toString(myTeamScore));

        int myTeamMiss = gameInfo.myTeamMiss();
        TextView playerMisses = (TextView) findViewById(R.id.myTeamMisses);
        playerMisses.setText(Integer.toString(myTeamMiss));

        int myTeamRebound = gameInfo.myTeamRebound();
        TextView playerRebs = (TextView) findViewById(R.id.myTeamRebounds);
        playerRebs.setText(Integer.toString(myTeamRebound));

        int myTeamFoul = gameInfo.myTeamFoul();
        TextView playerFouls = (TextView) findViewById(R.id.myTeamFouls);
        playerFouls.setText(Integer.toString(myTeamFoul));

        int myTeamTurnover = gameInfo.myTeamTurnover();
        TextView playerTurnovers = (TextView) findViewById(R.id.myTeamTurnovers);
        playerTurnovers.setText(Integer.toString(myTeamTurnover));


        int opposingTeamScore = gameInfo.opposingTeamScore();
        TextView opposingTeamPoints = (TextView) findViewById(R.id.opposingTeamPoints);
        opposingTeamPoints.setText(Integer.toString(opposingTeamScore));

        int opposingTeamMiss = gameInfo.opposingTeamMiss();
        TextView opposingTeamMisses = (TextView) findViewById(R.id.opposingMisses);
        opposingTeamMisses.setText(Integer.toString(opposingTeamMiss));

        int opposingTeamRebound = gameInfo.opposingTeamRebound();
        TextView opposingTeamRebs = (TextView) findViewById(R.id.opposingTeamRebounds);
        opposingTeamRebs.setText(Integer.toString(opposingTeamRebound));

        int opposingTeamFoul = gameInfo.opposingTeamFoul();
        TextView opposingTeamFouls = (TextView) findViewById(R.id.opposingTeamFouls);
        opposingTeamFouls.setText(Integer.toString(opposingTeamFoul));

        int opposingTeamTurnover = gameInfo.opposingTeamTurnover();
        TextView opposingTeamTurnovers = (TextView) findViewById(R.id.opposingTeamTurnovers);
        opposingTeamTurnovers.setText(Integer.toString(opposingTeamTurnover));
=======
        
        myTeamScore = gameInfo.myTeamScore();
        TextView playerPoints = (TextView) findViewById(R.id.myTeamScore);
        playerPoints.setText(Integer.toString(myTeamScore));

        opposingTeamScore = gameInfo.opposingTeamScore();
        TextView opposingTeamPoints = (TextView) findViewById(R.id.opposeTeamScore);
        opposingTeamPoints.setText(Integer.toString(opposingTeamScore));



//        myTeamMiss = gameInfo.myTeamMiss();
//        TextView playerMisses = (TextView) findViewById(R.id.myTeamMisses);
//        playerMisses.setText(Integer.toString(myTeamMiss));
//
//        myTeamRebound = gameInfo.myTeamRebound();
//        TextView playerRebs = (TextView) findViewById(R.id.myTeamRebounds);
//        playerRebs.setText(Integer.toString(myTeamRebound));
//
//        myTeamFoul = gameInfo.myTeamFoul();
//        TextView playerFouls = (TextView) findViewById(R.id.myTeamFouls);
//        playerFouls.setText(Integer.toString(myTeamFoul));
//
//        myTeamTurnover = gameInfo.myTeamTurnover();
//        TextView playerTurnovers = (TextView) findViewById(R.id.myTeamTurnovers);
//        playerTurnovers.setText(Integer.toString(myTeamTurnover));
//
//

//
//        opposingTeamMiss = gameInfo.opposingTeamMiss();
//        TextView opposingTeamMisses = (TextView) findViewById(R.id.opposingMisses);
//        opposingTeamMisses.setText(Integer.toString(opposingTeamMiss));
//
//        opposingTeamRebound = gameInfo.opposingTeamRebound();
//        TextView opposingTeamRebs = (TextView) findViewById(R.id.opposingTeamRebounds);
//        opposingTeamRebs.setText(Integer.toString(opposingTeamRebound));
//
//        opposingTeamFoul = gameInfo.opposingTeamFoul();
//        TextView opposingTeamFouls = (TextView) findViewById(R.id.opposingTeamFouls);
//        opposingTeamFouls.setText(Integer.toString(opposingTeamFoul));
//
//        opposingTeamTurnover = gameInfo.opposingTeamTurnover();
//        TextView opposingTeamTurnovers = (TextView) findViewById(R.id.opposingTeamTurnovers);
//        opposingTeamTurnovers.setText(Integer.toString(opposingTeamTurnover));
>>>>>>> GameButtonInteraction

    }
}