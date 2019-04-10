package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePageActivity extends Activity implements View.OnClickListener {
    private Button NewGameButton;
    private Button HistoryButton;
    private ArrayList<GameInfo> allGames;
    private static final int MAIN_ACTIVITY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        allGames = new ArrayList<>();

        Intent intent = getIntent();
        if(intent != null){
            GameInfo gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
            if(gameInfo != null){
                allGames.add(gameInfo);
            }
        }

        NewGameButton = (Button) findViewById(R.id.NewGameButton);
        HistoryButton = (Button) findViewById(R.id.HistoryButton);
        NewGameButton.setOnClickListener(this);
        HistoryButton.setOnClickListener(this);
    }


    public void onClick(View v){
        if(v.getId() == R.id.NewGameButton) {
            Intent intent = new Intent(this, CreateGameActivity.class);


            //GameInfo gameInfo = new GameInfo();
            //intent.putExtra("gameInfo", gameInfo);
            //startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);

            GameInfo gameInfo = new GameInfo();
            intent.putExtra("gameInfo", gameInfo);
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        } else if (v.getId() == R.id.HistoryButton){
            Intent intent = new Intent(this, ChooseHistoryActivity.class);
            GameInfo gameInfo = new GameInfo();
            intent.putExtra("allgames", allGames);

            startActivity(intent);
        }
    }
}
