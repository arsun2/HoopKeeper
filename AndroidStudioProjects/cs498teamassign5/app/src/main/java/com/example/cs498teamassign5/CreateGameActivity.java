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

public class CreateGameActivity extends Activity implements View.OnClickListener {
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private GameInfo gameInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        /*
        Intent intent = getIntent();
        gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        updatePlayerStats();*/
    }

    public void onClick(View v) {
        int ClickId = v.getId();
        if(ClickId == R.id.PlayerButton){
            Intent intent = new Intent(this, MainActivity.class);
            GameInfo gameInfo = new GameInfo();
            intent.putExtra("gameInfo", gameInfo);
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        }

    }

    public void updatePlayerStats(){

    }
}