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

import java.util.ArrayList;

public class PlayerStat extends Activity implements View.OnClickListener {
    private ArrayList<GameInfo> allGames;
    private static final int MAIN_ACTIVITY_REQUEST = 1;

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
}