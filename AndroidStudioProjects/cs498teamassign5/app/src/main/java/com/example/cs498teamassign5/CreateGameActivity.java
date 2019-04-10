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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGameActivity extends Activity implements View.OnClickListener {
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private Button StartButton;
    private EditText locationText;
    private EditText timeText;
    private EditText myPlayerText;
    private EditText myTeamText;
    private EditText opposingTeamText;

    private String location = "";
    private String time = "";
    private String myPlayer = "";
    private String myTeam = "";
    private String opposingTeam = "";
    private GameInfo gameInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        StartButton = (Button) findViewById(R.id.startButton);
        locationText = (EditText) findViewById(R.id.editText);
        timeText = (EditText) findViewById(R.id.TimeText);
        myPlayerText = (EditText) findViewById(R.id.playerText);
        myTeamText = (EditText) findViewById(R.id.MyTeamText);
        opposingTeamText = (EditText) findViewById(R.id.OpposingText);


        StartButton.setOnClickListener(this);
        /*
        Intent intent = getIntent();
        gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        updatePlayerStats();*/
    }

    public void onClick(View v) {
        int ClickId = v.getId();
        if(ClickId == R.id.startButton){
            Intent intent = new Intent(this, MainActivity.class);
            location = locationText.getText().toString();
            time = timeText.getText().toString();
            myPlayer = myPlayerText.getText().toString();
            myTeam = myTeamText.getText().toString();
            opposingTeam = opposingTeamText.getText().toString();

            Bundle toPass = new Bundle();
            GameInfo gameInfo = new GameInfo();
            toPass.putSerializable("gameInfo", gameInfo);
            toPass.putString("playerName", myPlayer);
            toPass.putString("myTeamName", myTeam);
            toPass.putString("opposingTeam", opposingTeam);
            toPass.putString("location", location);
            toPass.putString("time", time);
            intent.putExtras(toPass);
            //intent.putExtra("gameInfo", gameInfo);
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        }
    }

    public void updatePlayerStats(){

    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        //System.out.println("hey there");
        if (activityCode == MAIN_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                System.out.print("create game result\n");
                GameInfo gameinfo = (GameInfo)intent.getSerializableExtra("gameInfo");
                Intent resultIntent = new Intent(this, HomePageActivity.class);
                resultIntent.putExtra("gameInfo", gameinfo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}