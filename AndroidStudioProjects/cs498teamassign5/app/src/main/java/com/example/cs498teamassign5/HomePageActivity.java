package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HomePageActivity extends Activity implements View.OnClickListener {
    private Button NewGameButton;
    private ArrayList<GameInfo> allGames;
    private static final int MAIN_ACTIVITY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        allGames = new ArrayList<>();

        NewGameButton = (Button) findViewById(R.id.NewGameButton);
        NewGameButton.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.NewGameButton) {
            Intent intent = new Intent(this, MainActivity.class);
            GameInfo gameInfo = new GameInfo();
            intent.putExtra("quarter", 0);
            intent.putExtra("gameInfo", gameInfo);
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        }
    }
}
