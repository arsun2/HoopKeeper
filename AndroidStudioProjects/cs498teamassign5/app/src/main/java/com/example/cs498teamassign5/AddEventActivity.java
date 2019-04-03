package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddEventActivity extends Activity implements View.OnClickListener {
    private static final int ADD_SCORE_ACTIVITY_REQUEST = 1;
    private Button AddScoreButton;
    private Button FoulButton;
    private Button AssistButton;
    private Button ReboundButton;
    private Button BlockButton;
    private String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        AddScoreButton = (Button) findViewById(R.id.add_score_button);
        FoulButton = (Button) findViewById(R.id.foul_button);
        AssistButton = (Button) findViewById(R.id.assist_button);
        ReboundButton = (Button) findViewById(R.id.rebound_button);
        BlockButton = (Button) findViewById(R.id.block_button);

        AddScoreButton.setOnClickListener(this);
        FoulButton.setOnClickListener(this);
        AssistButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        BlockButton.setOnClickListener(this);
    }

    public void onClick(View v){
        int clickID = v.getId();
        if(clickID == R.id.add_score_button){
            Intent intent = new Intent(this, AddScoreActivity.class);
            startActivityForResult(intent, ADD_SCORE_ACTIVITY_REQUEST);
        } else if (clickID == R.id.foul_button){
//            Intent intent = new
        } else if (clickID == R.id.assist_button){

        } else if (clickID == R.id.rebound_button){

        } else if (clickID == R.id.block_button){

        }
    }
}
