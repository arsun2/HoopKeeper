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

public class PlayerStat extends Activity implements View.OnClickListener {
    private ArrayList<GameInfo> allGames;
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private int myPlayerScore = 0;
    private int myPlayerRebound = 0;
    private int myPlayerFoul = 0;
    private int myPlayerTurnover = 0;
    private String info = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stat);

        Intent i = getIntent();
        info = i.getStringExtra("ans");
        System.out.println("Player stats are: " + info);
        List<String> items = Arrays.asList(info.split("\\s*,\\s*"));
        if(items.size() == 4){
            int points = Integer.parseInt(items.get(0));
            int rebs = Integer.parseInt(items.get(1));
            int fouls = Integer.parseInt(items.get(2));
            int turnovers = Integer.parseInt(items.get(3));
            updatePlayerStats(points, rebs, fouls, turnovers);
        } else {
            updatePlayerStats(0, 0, 0, 0);
        }


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

    public void updatePlayerStats(int points, int rebs, int fouls, int turnovers){

        myPlayerScore = points;
        TextView playerPoints = (TextView) findViewById(R.id.PlayerPoints);
        playerPoints.setText(Integer.toString(myPlayerScore));

        myPlayerRebound = rebs;
        TextView playerRebs = (TextView) findViewById(R.id.PlayerRebounds);
        playerRebs.setText(Integer.toString(myPlayerRebound));

        myPlayerFoul = fouls;
        TextView playerFouls = (TextView) findViewById(R.id.PlayerFouls);
        playerRebs.setText(Integer.toString(myPlayerFoul));

        myPlayerTurnover = turnovers;
        TextView playerTurnovers = (TextView) findViewById(R.id.PlayerTurnovers);
        playerRebs.setText(Integer.toString(myPlayerTurnover));


    }
}